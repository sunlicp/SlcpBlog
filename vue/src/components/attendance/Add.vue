<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>出勤异常管理</el-breadcrumb-item>
      <el-breadcrumb-item>添加出勤异常信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 提示 -->
      <el-alert title="添加出勤异常信息" type="info" center show-icon :closable="false"></el-alert>
      <!-- 步骤条 -->
      <el-steps :space="200" :active="activeIndex - 0" finish-status="success" align-center>
        <el-step title="基本信息"></el-step>
        <el-step title="类型"></el-step>
        <el-step title="时长"></el-step>
        <el-step title="图片"></el-step>
        <el-step title="出勤异常内容"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>

      <!-- Tab栏 -->
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="100px"
        label-position="top"
      >
        <el-tabs
          v-model="activeIndex"
          :tab-position="'left'"
        >
          <el-tab-pane label="基本信息" name="0">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="addForm.name"></el-input>
            </el-form-item>
            <el-form-item label="学号" prop="student_number">
              <el-input v-model="addForm.student_number"></el-input>
            </el-form-item>
            <el-form-item label="年级" prop="grade">
              <el-input v-model="addForm.grade"></el-input>
            </el-form-item>
            <el-form-item label="班级" prop="class">
              <el-input v-model="addForm.class"></el-input>
            </el-form-item>
            <el-form-item label="辅导员" prop="instructor">
              <el-input v-model="addForm.instructor"></el-input>
            </el-form-item>
            <el-form-item label="登记人" prop="urealname">
              <el-input v-model="addForm.urealname"></el-input>
            </el-form-item>
          </el-tab-pane>
          <!-- 类型 -->
          <el-tab-pane label="类型" name="1">
            <el-form-item label="类型" prop="type">
              <el-input v-model="addForm.type"></el-input>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="时长" name="2">
            <el-form-item label="迟到时长" prop="length">
              <el-input v-model="addForm.length"></el-input>
            </el-form-item>
            <el-form-item label="时间" prop="date">
              <template>
                <div class="block">
                  <el-date-picker
                    v-model="addForm.date"
                    type="datetime"
                    placeholder="选择日期时间"
                    default-time="12:00:00">
                  </el-date-picker>
                </div>
              </template>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="出勤异常图片" name="3">
            <!-- action: 图片上传的API接口地址 -->
            <el-upload
              :action="uploadURL"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :headers="headerObj"
              list-type="picture"
              :on-success="handleSuccess"
            >
              <el-button size="small" type="primary">点击上传</el-button>
            </el-upload>
          </el-tab-pane>
          <el-tab-pane label="出勤异常内容" name="4">
            <!-- 富文本编辑器 -->
            <quill-editor v-model="addForm.remark"></quill-editor>
            <!-- 添加出勤异常 -->
            <el-button type="primary" class="btnAdd" @click="addAbnormals">添加出勤异常信息</el-button>
          </el-tab-pane>
        </el-tabs>
      </el-form>
    </el-card>
    <el-dialog title="图片预览" :visible.sync="previewDialogVisible" width="50%">
      <img :src="picPreviewPath" alt="" class="previewImg">
    </el-dialog>
  </div>
</template>

<script>
import _ from 'lodash'
export default {
  data () {
    return {
      // 步骤条默认激活 与左侧Tab联动
      activeIndex: '0',
      // 添加出勤异常的表单对象
      addForm: {
        name: '',
        student_number: '',
        grade: '',
        class: '',
        type: '',
        instructor: '',
        urealname: '',
        remark: '',
        date: '',
        length: '',
        // 图片的数组
        pics: [],
        // 出勤异常详情描述
        introduce: '',
        attrs: []
      },
      addFormRules: {
        name: [
          { required: true, message: '请输入学生姓名', trigger: 'blur' }
        ],
        student_number: [
          { required: true, message: '请输入学生学号', trigger: 'blur' }
        ],
        grade: [
          { required: true, message: '请输入学生年级', trigger: 'blur' }
        ],
        class: [
          { required: true, message: '请输入学生班级', trigger: 'blur' }
        ],
        instructor: [
          { required: true, message: '请输入辅导员', trigger: 'blur' }
        ],
        urealname: [
          { required: true, message: '请输入登记人', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请输入迟到类型', trigger: 'blur' }
        ],
        length: [
          { required: true, message: '请输入迟到时长', trigger: 'blur' }
        ],
        date: [
          { required: true, message: '请输入迟到时长', trigger: 'blur' }
        ]

      },
      // 出勤异常列表
      cateList: [],
      // 级联选择器配置
      cascaderProps: {
        expandTrigger: 'hover',
        label: 'cat_name',
        value: 'cat_id',
        children: 'children'
      },
      // 动态参数列表数据
      manyTableData: [],
      // 静态属性列表数据
      onlyTableData: [],
      // 图片上传地址
      uploadURL: 'http://127.0.0.1:8090/upload',
      // 图片上传组件的请求对象
      headerObj: {
        Authorization: window.sessionStorage.getItem('token')
      },
      picPreviewPath: '',
      // 图片预览对话框
      previewDialogVisible: false
    }
  },
  computed: {
    getCateId () {
      if (this.addForm.goods_cat.length === 3) {
        return this.addForm.goods_cat[2]
      }
      return null
    }
  },
  methods: {
    // 级联选择器选中项变化时出发
    handleChange () {
      if (this.addForm.goods_cat.length !== 3) {
        this.addForm.goods_cat = []
      }
    },
    // 处理图片预览
    handlePreview (file) {
      this.picPreviewPath = file.response.data.url
      this.previewDialogVisible = true
    },
    // 处理移除图片的操作
    handleRemove (file) {
      // 1.获取将要删除图片的临时路径
      const filePath = file.response.data.tmp_path
      // 2.从pics数组中，找到图片对应的索引值
      const i = this.addForm.pics.findIndex(x => x.pic === filePath)
      // 3.调用splice方法，移除图片信息
      this.addForm.splice(i, 1)
    },
    // 监听图片上传成功事件
    handleSuccess (response) {
      // 1.拼接得到一个图片信息对象 临时路径
      const picInfo = { pic: response.data.tmp_path }
      // 2.将图片信息对象，push到pics数组中
      this.addForm.pics.push(picInfo)
    },
    // 添加出勤异常
    addAbnormals () {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return this.$message.error('请填写必要的表单项！')
        // 发起请求添加出勤异常
        // 出勤异常名称必须是唯一的
        if (!valid) return this.$message.error('请填写必要的表单项！')
        const { data: res } = await this.$http.post('abnormal', {
          name: this.addForm.name,
          type: this.addForm.type,
          length: this.addForm.length,
          date: this.addForm.date,
          remark: this.addForm.remark,
          uid: 3
        })
        if (res.meta.status !== 200) return this.$message.error('添加出勤异常失败！')
        this.$message.success('添加出勤异常成功!')
        this.$router.push('/abnormals')
      })
    }
  }
}
</script>

<style lang='less' scoped>
.el-checkbox {
  margin: 0 8px 0 0 !important;
}
.previewImg{
  width: 100%;
}
.btnAdd{
  margin-top: 15px
}
</style>
