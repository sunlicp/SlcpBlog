<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>图库管理</el-breadcrumb-item>
      <el-breadcrumb-item>图片列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区域 -->
    <el-card>
      <!-- 搜索与添加区域 -->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getPictureList">
            <el-button slot="append" icon="el-icon-search" @click="getPictureList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="addDialogVisible = true">添加</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="uploadDialogVisible = true">上传</el-button>
        </el-col>
      </el-row>

      <!-- 图库列表区域 -->
      <el-table :data="picturelist" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="图片名" prop="pictureName"></el-table-column>
        <el-table-column label="图义" prop="pictureDescription"></el-table-column>
        <el-table-column label="地址" prop="pictureAddress">
          <template slot-scope="scope">
            <img :src="scope.row.pictureAddress" class="mi-img"/>
          </template>
        </el-table-column>
        <el-table-column label="级别" prop="pictureLevel" width="80px">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.pictureLevel == '0'">一级</el-tag>
            <el-tag type="success" v-else-if="scope.row.pictureLevel == '1'">二级</el-tag>
            <el-tag type="danger" v-else>三级</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="130px">
          <template slot-scope="scope">
            <!-- 修改按钮 -->
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <!-- 删除按钮 -->
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removePictureById(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </el-card>

    <!-- 添加图库的对话框 -->
    <el-dialog title="添加图片" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <!-- 内容主体区域 -->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="图库名" prop="pictureName">
          <el-input v-model="addForm.pictureName"></el-input>
        </el-form-item>
        <el-form-item label="图义" prop="pictureDescription">
          <el-input v-model="addForm.pictureDescription"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="pictureAddress">
          <el-input v-model="addForm.pictureAddress"></el-input>
        </el-form-item>
        <el-form-item label="级别" prop="pictureLevel">
          <el-select v-model="addForm.pictureLevel" placeholder="请选择">
            <el-option label="一级" value="1">
            </el-option>
            <el-option label="二级" value="2">
            </el-option>
            <el-option label="三级" value="3">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 上传图库的对话框 -->
    <el-dialog title="上传图片" :visible.sync="uploadDialogVisible" width="50%" @close="uploadDialogClosed">
      <!-- 内容主体区域 -->
      <el-form :model="uploadForm" :rules="uploadFormRules" ref="uploadFormRef" label-width="70px">
        <el-form-item label="图库名" prop="pictureName">
          <el-input v-model="uploadForm.pictureName"></el-input>
        </el-form-item>
        <el-form-item label="图义" prop="pictureDescription">
          <el-input v-model="uploadForm.pictureDescription"></el-input>
        </el-form-item>
        <el-form-item label="级别" prop="pictureLevel">
          <el-select v-model="uploadForm.pictureLevel" placeholder="请选择">
            <el-option label="一级" value="1">
            </el-option>
            <el-option label="二级" value="2">
            </el-option>
            <el-option label="三级" value="3">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上传" prop="pictureAddress">
          <el-upload
            drag
            multiple
            class="avatar-uploader"
            action="https://slcp.top/sys/pictures/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="uploadForm.pictureAddress!=''" :src="uploadForm.pictureAddress" class="avatar me-video-player">
            <div v-else>
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip" style="margin-bottom: 15px;">只能上传jpg/png文件，且不超过2mb</div>
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="uploadUser">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改图库的对话框 -->
    <el-dialog title="修改图库" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="图库名" prop="pictureName">
          <el-input v-model="editForm.pictureName"></el-input>
        </el-form-item>
        <el-form-item label="图义" prop="pictureDescription">
          <el-input v-model="editForm.pictureDescription"></el-input>
        </el-form-item>
        <el-form-item label="级别" prop="pictureLevel">
          <el-select v-model="editForm.pictureLevel" placeholder="请选择">
            <el-option label="一级" value="1">
            </el-option>
            <el-option label="二级" value="2">
            </el-option>
            <el-option label="三级" value="3">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="地址" prop="pictureAddress">
          <el-upload
            drag
            multiple
            class="avatar-uploader"
            action="https://slcp.top/sys/pictures/upload"
            :show-file-list="false"
            :on-success="editHandleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="editForm.pictureAddress!=''" :src="editForm.pictureAddress" class="avatar me-video-player">
            <div v-else>
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip" style="margin-bottom: 15px;">只能上传jpg/png文件，且不超过2mb</div>
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editPictureInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 获取图库列表的参数对象
      value:true,
      queryInfo: {
        id: 0,
        query: '',
        // 当前的页数
        pageNum: 1,
        // 当前每页显示多少条数据
        pageSize: 2
      },
      picturelist: [],
      total: 0,
      // 控制添加图库对话框的显示与隐藏
      addDialogVisible: false,
      // 添加图库的表单数据
      addForm: {
        pictureName: '',
        pictureAddress: '',
        pictureDescription: '',
        pictureLevel: ''
      },
      // 添加表单的验证规则对象
      addFormRules: {
        pictureAddress: [
          { required: true, message: '请输入图片地址', trigger: 'blur' }
        ],
        pictureName: [
          { required: true, message: '请输入图片名称', trigger: 'blur' }
        ],
        pictureDescription: [
          { required: true, message: '请输入图片内容', trigger: 'blur' }
        ],
        pictureLevel: [
          { required: true, message: '请输入图片层次', trigger: 'blur' }
        ]
      },
      // 控制上传图库对话框的显示与隐藏
      uploadDialogVisible: false,
      // 上传图库的表单数据
      uploadForm: {
        pictureName: '',
        pictureAddress: '',
        pictureDescription: '',
        pictureLevel: ''
      },
      // 上传表单的验证规则对象
      uploadFormRules: {
        pictureName: [
          { required: true, message: '请输入图片名称', trigger: 'blur' }
        ],
        pictureDescription: [
          { required: true, message: '请输入图片内容', trigger: 'blur' }
        ]
      },
      // 控制修改图库对话框的显示与隐藏
      editDialogVisible: false,
      // 查询到的图库信息对象
      editForm: {},
      // 修改表单的验证规则对象
      editFormRules: {
        pictureAddress: [
          { required: true, message: '请输入图片地址', trigger: 'blur' }
        ],
        pictureName: [
          { required: true, message: '请输入图片名称', trigger: 'blur' }
        ],
        pictureDescription: [
          { required: true, message: '请输入图片内容', trigger: 'blur' }
        ],
        pictureLevel: [
          { required: true, message: '请输入图片层次', trigger: 'blur' }
        ]
      },
      // 需要被分配角色的图库信息
      pictureinfo: {},
      // 所有角色的数据列表
      rolesList: [],
      // 已选中的角色Id值
      selectedRoleId: '',
      roles: {}
    }
  },
  created() {
    this.getPictureList()
  },
  methods: {
    async getPictureList() {
      const { data: res } = await this.$http.get('picture', {
        params: this.queryInfo
      })
      if (res.code !== 200) {
      //   this.$router.push('/no')
      //   return this.$message.error('权限不够，无法获取图库列表！')
        return this.$message.error(res.msg)
      }

      this.picturelist = res.data.records
      this.total = res.data.total
    },
    // 监听 pagesize 改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize
      this.getPictureList()
    },
    // 监听 页码值 改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage
      this.getPictureList()
    },
    // 监听添加图库对话框的关闭事件
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },
    // 点击按钮，添加新图库
    addUser() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加图库的网络请求
        const { data: res } = await this.$http.post('picture', this.addForm)
        if (res.code !== 200) {
          this.$message.error('添加图库失败！')
        }

        this.$message.success('添加图库成功！')
        // 隐藏添加图库的对话框
        this.addDialogVisible = false
        // 重新获取图库列表数据
        this.getPictureList()
      })
    },
    // 监听添加图库对话框的关闭事件
    uploadDialogClosed() {
      this.$refs.uploadFormRef.resetFields()
    },
    //图片回显
    handleAvatarSuccess(res, file) {
      if(res.code !== 200) return this.$message.error(res.msg);
      this.uploadForm.pictureAddress = res.msg
    },
    editHandleAvatarSuccess(res, file) {
      if(res.code !== 200) return this.$message.error(res.msg);
      this.editForm.pictureAddress = res.msg
    },
    //图片上传大小限制
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isLt2M;
    },
    // 点击按钮，添加新图库
    uploadUser() {
      this.$refs.uploadFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加图库的网络请求
        const { data: res } = await this.$http.post('picture', this.uploadForm)
        if (res.code !== 200) {
          this.$message.error('添加图库失败！')
        }

        this.$message.success('添加图库成功！')
        // 隐藏添加图库的对话框
        this.uploadDialogVisible = false
        // 重新获取图库列表数据
        this.getPictureList()
      })
    },
    // 展示编辑图库的对话框
    async showEditDialog(id) {
      const { data: res } = await this.$http.get('picture/' + id)

      if (res.code !== 200) {
        return this.$message.error('查询图库信息失败！')
      }

      this.editForm = res.data
      this.editDialogVisible = true
    },
    // 监听修改图库对话框的关闭事件
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    // 修改图库信息并提交
    editPictureInfo() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 发起修改图库信息的数据请求
        const { data: res } = await this.$http.post('picture', this.editForm)
        if (res.code !== 200) {
          return this.$message.error('更新图库信息失败！')
        }

        // 关闭对话框
        this.editDialogVisible = false
        // 刷新数据列表
        this.getPictureList()
        // 提示修改成功
        this.$message.success('更新图库信息成功！')
      })
    },
    // 根据Id删除对应的图库信息
    async removePictureById(id) {
      // 弹框询问图库是否删除数据
      const confirmResult = await this.$confirm(
        '此操作将永久删除该图库, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)

      // 如果图库确认删除，则返回值为字符串 confirm
      // 如果图库取消了删除，则返回值为字符串 cancel
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }

      const { data: res } = await this.$http.delete('picture/' + id)

      if (res.code !== 200) {
        return this.$message.error('删除图库失败！')
      }

      this.$message.success('删除图库成功！')
      this.getPictureList()
    }
  }
}
</script>

<style lang="less" scoped>
</style>
