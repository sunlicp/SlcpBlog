<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>友链管理</el-breadcrumb-item>
      <el-breadcrumb-item>友链列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区域 -->
    <el-card>
      <!-- 搜索与添加区域 -->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getFriendList">
            <el-button slot="append" icon="el-icon-search" @click="getFriendList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加友链</el-button>
        </el-col>
      </el-row>

      <!-- 友链列表区域 -->
      <el-table :data="friendlist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="友链名" prop="blogName"></el-table-column>
        <el-table-column label="地址" prop="blogAddress"></el-table-column>
        <el-table-column label="语录" prop="description"></el-table-column>
        <el-table-column label="头像地址" prop="headImg">
          <template slot-scope="scope">
            <img :src="scope.row.headImg" class="mi-img"/>
          </template>
        </el-table-column>
        <el-table-column label="首页图片" prop="blogImg">
          <template slot-scope="scope">
            <img :src="scope.row.blogImg" class="mi-img"/>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="80px">
          <template slot-scope="scope">
            <!-- v-model无敌巨坑，不带开关值设置，传值必须为boolean。带:为数值，不带为string -->
            <el-switch v-model="scope.row.status" @change="friendStateChanged(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="130px">
          <template slot-scope="scope">
            <!-- 修改按钮 -->
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <!-- 删除按钮 -->
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeFriendById(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </el-card>

    <!-- 添加友链的对话框 -->
    <el-dialog title="添加友链" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体区域 -->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="友链名" prop="blogName">
          <el-input v-model="addForm.blogName"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="blogAddress">
          <el-input v-model="addForm.blogAddress"></el-input>
        </el-form-item>
        <el-form-item label="语录" prop="description">
          <el-input v-model="addForm.description"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="headImg">
          <el-input v-model="addForm.headImg"></el-input>
        </el-form-item>
        <el-form-item label="首图" prop="blogImg">
          <el-input v-model="addForm.blogImg"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 修改友链的对话框 -->
    <el-dialog title="修改友链" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="友链名" prop="blogName">
          <el-input v-model="editForm.blogName"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="blogAddress">
          <el-input v-model="editForm.blogAddress"></el-input>
        </el-form-item>
        <el-form-item label="语录" prop="description">
          <el-input v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="头像" prop="headImg">
          <el-input v-model="editForm.headImg"></el-input>
        </el-form-item>
        <el-form-item label="首图" prop="blogImg">
          <el-input v-model="editForm.blogImg"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editFriendInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 获取友链列表的参数对象
      value:true,
      queryInfo: {
        id: 0,
        query: '',
        // 当前的页数
        pageNum: 1,
        // 当前每页显示多少条数据
        pageSize: 2
      },
      friendlist: [],
      total: 0,
      // 控制添加友链对话框的显示与隐藏
      addDialogVisible: false,
      // 添加友链的表单数据
      addForm: {
        blogAddress: '',
        blogName: '',
        headImg: '',
        blogImg: '',
        description: ''
      },
      // 添加表单的验证规则对象
      addFormRules: {
        blogAddress: [
          { required: true, message: '请输入友链地址', trigger: 'blur' }
        ],
        blogName: [
          { required: true, message: '请输入友链名', trigger: 'blur' }
        ],
        headImg: [
          { required: true, message: '请输入头像地址', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入博客语录', trigger: 'blur' }
        ]
      },
      // 控制修改友链对话框的显示与隐藏
      editDialogVisible: false,
      // 查询到的友链信息对象
      editForm: {},
      // 修改表单的验证规则对象
      editFormRules: {
        blogAddress: [
          { required: true, message: '请输入友链地址', trigger: 'blur' }
        ],
        blogName: [
          { required: true, message: '请输入友链名', trigger: 'blur' }
        ],
        headImg: [
          { required: true, message: '请输入头像地址', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入博客语录', trigger: 'blur' }
        ]
      },
      // 需要被分配角色的友链信息
      friendinfo: {},
      // 所有角色的数据列表
      rolesList: [],
      // 已选中的角色Id值
      selectedRoleId: '',
      roles: {}
    }
  },
  created() {
    this.getFriendList()
  },
  methods: {
    async getFriendList() {
      const { data: res } = await this.$http.get('friend', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
      //   this.$router.push('/no')
      //   return this.$message.error('权限不够，无法获取友链列表！')
        return this.$message.error(res.msg)
      }

      this.friendlist = res.data.records
      this.total = res.data.total
    },
    // 监听 pagesize 改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getFriendList()
    },
    // 监听 页码值 改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getFriendList()
    },
    // 监听 switch 开关状态的改变
    async friendStateChanged(friendinfo) {
      const { data: res } = await this.$http.get('friend/'+ friendinfo.id +"/" + friendinfo.status)
      if (res.code !== 200) {
        friendinfo.status = !friendinfo.status
        return this.$message.error('更新友链状态失败！')
      }
      this.$message.success('更新友链状态成功！')
    },
    // 监听添加友链对话框的关闭事件
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    // 点击按钮，添加新友链
    addUser() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加友链的网络请求
        const { data: res } = await this.$http.post('friend', this.addForm)
        if (res.code !== 200) {
          this.$message.error('添加友链失败！')
        }

        this.$message.success('添加友链成功！')
        // 隐藏添加友链的对话框
        this.addDialogVisible = false
        // 重新获取友链列表数据
        this.getFriendList()
      })
    },
    // 展示编辑友链的对话框
    async showEditDialog(id) {
      const { data: res } = await this.$http.get('friend/' + id)

      if (res.code !== 200) {
        return this.$message.error('查询友链信息失败！')
      }

      this.editForm = res.data
      this.editDialogVisible = true
    },
    // 监听修改友链对话框的关闭事件
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    // 修改友链信息并提交
    editFriendInfo() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 发起修改友链信息的数据请求
        const { data: res } = await this.$http.post('friend', this.editForm)
        if (res.code !== 200) {
          return this.$message.error('更新友链信息失败！')
        }

        // 关闭对话框
        this.editDialogVisible = false
        // 刷新数据列表
        this.getFriendList()
        // 提示修改成功
        this.$message.success('更新友链信息成功！')
      })
    },
    // 根据Id删除对应的友链信息
    async removeFriendById(id) {
      // 弹框询问友链是否删除数据
      const confirmResult = await this.$confirm(
        '此操作将永久删除该友链, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)

      // 如果友链确认删除，则返回值为字符串 confirm
      // 如果友链取消了删除，则返回值为字符串 cancel
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }

      const { data: res } = await this.$http.delete('friend/' + id)

      if (res.code !== 200) {
        return this.$message.error('删除友链失败！')
      }

      this.$message.success('删除友链成功！')
      this.getFriendList()
    }
  }
}
</script>

<style lang="less" scoped>
</style>
