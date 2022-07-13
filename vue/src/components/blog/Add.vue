<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>博客管理</el-breadcrumb-item>
      <el-breadcrumb-item>添加博客信息</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 提示 -->
      <el-alert title="添加博客信息" type="info" center show-icon :closable="false"></el-alert>
      <!-- 步骤条 -->
      <el-steps :space="200" :active="activeIndex - 0" finish-status="success" align-center>
        <el-step title="基本信息"></el-step>
        <el-step title="博客内容"></el-step>
        <el-step title="文章标签"></el-step>
        <el-step title="封面图片"></el-step>
        <el-step title="发布形式"></el-step>
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
          
          <el-tab-pane label="博客内容" name="0">
            <el-form-item label="文章内容" prop="content">
            <mavon-editor v-model="addForm.content"/>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="基本信息" name="1">
            <el-form-item label="文章类型" prop="flag">
              <el-select v-model="addForm.flag" clearable placeholder="请选择">
                <el-option label="原创" :key="1" :value="1">
                </el-option>
                <el-option label="转载" :key="2" :value="2">
                </el-option>
                <el-option label="翻译" :key="3" :value="3">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="分类专栏" prop="typeId">
              <el-select v-model="addForm.typeId" filterable placeholder="请选择">
                <el-option v-for="item in typeList" :key="item.typeId" :label="item.typeName" :value="item.typeId">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="标题" prop="title">
              <el-input v-model="addForm.title"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="description">
              <el-input v-model="addForm.description"></el-input>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="文章标签" name="2">
            <el-form-item label="标签" prop="tagIds">
              <div style="margin: 15px 0;"></div>
              <el-checkbox-group v-model="addForm.tagIds">
                <el-checkbox class="btnAdd" v-for="tag in tagList" :style="tag.color" :label="tag.tagId" :key="tag.tagId" border>{{tag.tagName}}</el-checkbox>
              </el-checkbox-group>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="封面图片" name="3">
              <!-- action: 图片上传的API接口地址 -->
              <el-upload
                drag
                multiple
                class="avatar-uploader"
                :action="uploadURL"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="addForm.firstPicture!=''" :src="addForm.firstPicture" class="avatar me-video-player">
                <div v-else>
                  <i class="el-icon-upload"></i>
                  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                  <div class="el-upload__tip" slot="tip" style="margin-bottom: 15px;">只能上传jpg/png文件，且不超过2mb</div>
                </div>
              </el-upload>
          </el-tab-pane>
          <!-- 发布形式 -->
          <el-tab-pane label="发布形式" name="4">
                        <!-- 添加博客 -->
            <div class="addButton">
              <el-button type="info" class="btnAdd" @click="cancelBlog" round>取消</el-button>
              <el-button type="success" class="btnAdd" @click="draftBlog" round>保存为草稿</el-button>
              <el-button type="primary" class="btnAdd" @click="timingBlog" round>定时发布</el-button>
              <el-button type="danger" class="btnAdd" @click="addBlog" round>发布文章</el-button>
            </div>
            <el-form-item label="公开" prop="published">
              <el-switch v-model="addForm.published"></el-switch>
            </el-form-item>
            <el-form-item label="置顶" prop="top">
              <el-switch v-model="addForm.top"></el-switch>
            </el-form-item>
            <el-form-item label="推荐" prop="recommend">
              <el-switch v-model="addForm.recommend"></el-switch>
            </el-form-item>
            <el-form-item label="转载声明" prop="shareStatement">
              <el-switch v-model="addForm.shareStatement"></el-switch>
            </el-form-item>
            <el-form-item label="评论" prop="comment">
              <el-switch v-model="addForm.comment"></el-switch>
            </el-form-item>
            <el-form-item label="赞赏" prop="appreciation">
              <el-switch v-model="addForm.appreciation"></el-switch>
            </el-form-item>
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
  const tagOptions= [{tagId: 1, tagName: '长沙', color: 'color:#67c23a;border-color:#67c23a'},{tagId: 2, tagName: '长沙', color: 'color:#e18cff;border-color:#e18cff'},{tagId: 3, tagName: '长沙', color: 'color:#e6a23c;border-color:#e6a23c'},{tagId: 4, tagName: '长沙', color: 'color:#40eaff;border-color:#40eaff'},{tagId: 5, tagName: '长沙', color: 'color:#4c40ff;border-color:#4c40ff'},{tagId: 6, tagName: '上海', color: 'color:#4c40ff'},{tagId: 7, tagName: '上海', color: 'color:#e6663c'},{tagId: 8, tagName: '上海', color: 'color:#dce6dd'},{tagId: 9, tagName: '上海', color: 'color:#e63c66'}];
export default {
  data () {
    return {
      // 步骤条默认激活 与左侧Tab联动
      activeIndex: '0',
      // 添加博客的表单对象
      addForm: {
        flag: '',
        userId: '',
        typeId: '',
        tagIds: [],
        content: '',
        title: '',
        description: '',
        firstPicture: '',
        published: true,
        appreciation: true,
        comment: true,
        recommend: true,
        shareStatement: true,
        top: true
      },
      addFormRules: {
        typeId: [
          { required: true, message: '请输入分类专栏', trigger: 'blur' }
        ],
        tagIds: [
          { required: true, message: '请输入文章标签', trigger: 'blur' }
        ],
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入描述', trigger: 'blur' }
        ],
        flag: [
          { required: true, message: '请输入文章类型', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入文本内容', trigger: 'blur' }
        ]
      },
      // 博客列表
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
      uploadURL: 'https://slcp.top/sys/pictures/upload',
      // 图片上传组件的请求对象
      headerObj: {
        Authorization: window.sessionStorage.getItem('token')
      },
      picPreviewPath: '',
      // 图片预览对话框
      previewDialogVisible: false,
      checkedTagList: [],
      tagList: [],
      typeList: []
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
  created () {
    this.getList();
    this.addForm.userId =  window.sessionStorage.getItem('id');
  },
  methods: {
    async getList() {
      const { data: res } = await this.$http.get('/blog/add/color?id=' + this.$route.query.id)
      if(res.code !== 200) return this.$message.error(res.msg);
      this.tagList = res.data.tagList
      this.typeList = res.data.typeList
      console.log(res.data.editBlogInfo)
      if(res.data.editBlogInfo != 'null' && res.data.editBlogInfo) {
        this.addForm = res.data.editBlogInfo
        this.checkedTagList = res.data.editBlogInfo.tagIds
      }
    },
    // 级联选择器选中项变化时出发
    handleChange () {
      if (this.addForm.goods_cat.length !== 3) {
        this.addForm.goods_cat = []
      }
    },
    //图片回显
    handleAvatarSuccess(res, file) {
      if(res.code !== 200) return this.$message.error(res.msg);
      this.addForm.firstPicture = res.msg
    },
    //图片上传大小限制
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isLt2M;
    },
    // 取消博客
    cancelBlog () {
      this.$router.push('/blog')
    },
    // 草稿 将发布设为false即可
    draftBlog () {
        
    },
    // 发布  提供对话框实现即可
    timingBlog () {
      
    },
    // 添加博客
    addBlog () {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return this.$message.error('请填写必要的表单项！')
        // 发起请求添加博客
        // 博客名称必须是唯一的

        const { data: res } = await this.$http.post('/blog/add', this.addForm)
        if (res.code !== 200) return this.$message.error('添加博客失败！')
        this.$message.success('添加博客成功!')
        this.$router.push('/blog')
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
.el-checkbox {
  margin: 8px 8px 0 0 !important
}
.addButton {
  margin-right: 10%; 
  float:right;
}
</style>
