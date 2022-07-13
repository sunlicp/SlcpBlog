<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>分类管理</el-breadcrumb-item>
      <el-breadcrumb-item>分类列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区域 -->
    <el-card>
      <!-- 搜索与添加区域 -->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getTypeList">
            <el-button slot="append" icon="el-icon-search" @click="getTypeList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加分类</el-button>
        </el-col>
      </el-row>

      <!-- 分类列表区域 -->
      <el-table :data="typelist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="分类名" prop="name"></el-table-column>
        <el-table-column label="创建时间" prop="createTime"></el-table-column>
        <el-table-column label="更新时间" prop="updateTime"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改按钮 -->
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <!-- 删除按钮 -->
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeTypeById(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[5, 10, 15, 20]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </el-card>

    <!-- 添加分类的对话框 -->
    <el-dialog title="添加分类" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体区域 -->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="分类名" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改分类的对话框 -->
    <el-dialog title="修改分类" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="分类名" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editTypeInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 获取分类列表的参数对象
      value:true,
      queryInfo: {
        id: 0,
        query: '',
        // 当前的页数
        pageNum: 1,
        // 当前每页显示多少条数据
        pageSize: 5
      },
      typelist: [],
      total: 0,
      // 控制添加分类对话框的显示与隐藏
      addDialogVisible: false,
      // 添加分类的表单数据
      addForm: {
        name: ''
      },
      // 添加表单的验证规则对象
      addFormRules: {
        name: [
          { required: true, message: '请输入分类地址', trigger: 'blur' }
        ]
      },
      // 控制修改分类对话框的显示与隐藏
      editDialogVisible: false,
      // 查询到的分类信息对象
      editForm: {},
      // 修改表单的验证规则对象
      editFormRules: {
        name: [
          { required: true, message: '请输入分类地址', trigger: 'blur' }
        ]
      },
      // 需要被分配角色的分类信息
      typeinfo: {},
      // 所有角色的数据列表
      rolesList: [],
      // 已选中的角色Id值
      selectedRoleId: '',
      roles: {}
    }
  },
  created() {
    this.getTypeList()
  },
  methods: {
    async getTypeList() {
      const { data: res } = await this.$http.get('type', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
      //   this.$router.push('/no')
      //   return this.$message.error('权限不够，无法获取分类列表！')
        return this.$message.error(res.msg)
      }

      this.typelist = res.data.records
      this.total = res.data.total
    },
    // 监听 pagesize 改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getTypeList()
    },
    // 监听 页码值 改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getTypeList()
    },
    // 监听添加分类对话框的关闭事件
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    // 点击按钮，添加新分类
    addUser() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加分类的网络请求
        const { data: res } = await this.$http.post('type', this.addForm)
        if (res.code !== 200) {
          this.$message.error('添加分类失败！')
        }

        this.$message.success('添加分类成功！')
        // 隐藏添加分类的对话框
        this.addDialogVisible = false
        // 重新获取分类列表数据
        this.getTypeList()
      })
    },
    // 展示编辑分类的对话框
    async showEditDialog(id) {
      const { data: res } = await this.$http.get('type/' + id)

      if (res.code !== 200) {
        return this.$message.error('查询分类信息失败！')
      }

      this.editForm = res.data
      this.editDialogVisible = true
    },
    // 监听修改分类对话框的关闭事件
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    // 修改分类信息并提交
    editTypeInfo() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 发起修改分类信息的数据请求
        const { data: res } = await this.$http.post('type', this.editForm)
        if (res.code !== 200) {
          return this.$message.error('更新分类信息失败！')
        }

        // 关闭对话框
        this.editDialogVisible = false
        // 刷新数据列表
        this.getTypeList()
        // 提示修改成功
        this.$message.success('更新分类信息成功！')
      })
    },
    // 根据Id删除对应的分类信息
    async removeTypeById(id) {
      // 弹框询问分类是否删除数据
      const confirmResult = await this.$confirm(
        '此操作将永久删除该分类, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)

      // 如果分类确认删除，则返回值为字符串 confirm
      // 如果分类取消了删除，则返回值为字符串 cancel
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }

      const { data: res } = await this.$http.delete('type/' + id)

      if (res.code !== 200) {
        return this.$message.error('删除分类失败！')
      }

      this.$message.success('删除分类成功！')
      this.getTypeList()
    }
  }
}
</script>

<style lang="less" scoped>
.me-video-player {
  background-color: transparent;
  width: 100%;
  height: 100%;
  object-fit: fill;
  display: block;
  left: 0;
  z-index: 0;
  top: 0;
}
</style>
