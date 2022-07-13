<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>博客管理</el-breadcrumb-item>
      <el-breadcrumb-item>博客列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getBlogsList">
            <el-button slot="append" icon="el-icon-search" @click="getBlogsList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="goAddPage">添加博客信息</el-button>
        </el-col>
      </el-row>
      <!-- 表格数据 -->
      <el-table :data="blogsList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="标题" prop="title"></el-table-column>
        <el-table-column label="图片" prop="firstPicture">
          <template slot-scope="scope">
            <img :src="scope.row.firstPicture" class="mi-img" @mouseover="mouseoverImg(scope.row)"/>
          </template>
        </el-table-column>
        <el-table-column label="类型" prop="typeName"></el-table-column>
        <el-table-column label="访问次数" prop="views" width="70px"></el-table-column>
        <el-table-column label="推荐" prop="recommend" width="70px">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.recommend" @change="recommendStateChanged(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="赞赏" prop="appreciation" width="70px">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.appreciation" @change="appreciationStateChanged(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="置顶" prop="top" width="70px">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.top" @change="topStateChanged(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="公布" prop="published" width="70px">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.published" @change="publishedStateChanged(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="转载声明" prop="shareStatement" width="70px">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.shareStatement" @change="shareStatementStateChanged(scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="update(scope.row.id)"></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeById(scope.row.id)"
            ></el-button>
            <!-- 分配类型按钮 -->
            <el-tooltip effect="dark" content="分配类型" placement="top" :enterable="false">
              <el-button type="warning" icon="el-icon-setting" size="mini" @click="setType(scope.row)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        background
      ></el-pagination>
    </el-card>
    
    <!-- 分配类型的对话框 -->
    <el-dialog title="分配类型" :visible.sync="setTypeDialogVisible" width="50%" @close="setTypeDialogClosed">
      <div>
        <p>当前的标题：{{blogInfo.title}}</p>
        <p>当前的类型：{{blogInfo.typeName}}</p>
        <p>分配新类型：
          <el-select v-model="selectedTypeId" placeholder="请选择">
            <el-option v-for="item in typeList" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </p>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="setTypeDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveTypeInfo">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog :title="imgTitle" :visible.sync="dialogFormVisible">
      <img :src="imgPath" class="mx-img"  @mouseout="dialogFormVisible=false"/>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      queryInfo: {
        query: '',
        pageNum: 1,
        pageSize: 5,
        descs: 'updateTime',
      },
      // 博客信息列表
      blogsList: [],
      // 博客信息总数
      total: 0,
      // 控制分配类型对话框的显示与隐藏
      setTypeDialogVisible: false,
      // 需要被分配类型的博客信息
      blogInfo: {},
      // 所有类型的数据列表
      typeList: [],
      // 已选中的类型Id值
      selectedTypeId: '',
      types: {
        typeId: 0,
        typeName: ''
      },
      imgPath: '',
      imgTitle: '',
      dialogFormVisible: false
    }
  },
  created () {
    this.getBlogsList()
  },
  methods: {
    update(id) {
      this.$router.push('/blog/add?id='+ id)
    },
    // 监听 switch 开关状态的改变
    async publishedStateChanged(bloginfo) {
      const { data: res } = await this.$http.post('blog', {
        id: bloginfo.id,
        published: bloginfo.published
      })
      if (res.code !== 200) {
        bloginfo.published = !bloginfo.published
        return this.$message.error('更新博客失败！')
      }
      this.$message.success('更新博客成功！')
    },
        // 监听 switch 开关状态的改变
    async appreciationStateChanged(bloginfo) {
      const { data: res } = await this.$http.post('blog', {
        id: bloginfo.id,
        appreciation: bloginfo.appreciation
      })
      if (res.code !== 200) {
        bloginfo.appreciation = !bloginfo.appreciation
        return this.$message.error('更新博客失败！')
      }
      this.$message.success('更新博客成功！')
    },
        // 监听 switch 开关状态的改变
    async recommendStateChanged(bloginfo) {
      const { data: res } = await this.$http.post('blog', {
        id: bloginfo.id,
        recommend: bloginfo.recommend
      })
      if (res.code !== 200) {
        bloginfo.recommend = !bloginfo.recommend
        return this.$message.error('更新博客失败！')
      }
      this.$message.success('更新博客成功！')
    },
    // 监听 switch 开关状态的改变
    async topStateChanged(bloginfo) {
      const { data: res } = await this.$http.post('blog', {
        id: bloginfo.id,
        top: bloginfo.top
      })
      if (res.code !== 200) {
        bloginfo.top = !bloginfo.top
        return this.$message.error('更新博客失败！')
      }
      this.$message.success('更新博客成功！')
    },
        // 监听 switch 开关状态的改变
    async shareStatementStateChanged(bloginfo) {
      const { data: res } = await this.$http.post('blog', {
        id: bloginfo.id,
        shareStatement: bloginfo.shareStatement
      })
      if (res.code !== 200) {
        bloginfo.shareStatement = !bloginfo.shareStatement
        return this.$message.error('更新博客失败！')
      }
      this.$message.success('更新博客成功！')
    },
    // 根据分页获取对应的博客信息列表
    async getBlogsList () {
      const { data: res } = await this.$http.get('blog', {
        params: this.queryInfo
      })
    
      if (res.code !== 200) {
        return this.$message.error('获取博客信息列表失败！')
      }
      this.blogsList = res.data.records
      this.total = res.data.total
    },
    handleSizeChange (newSize) {
      this.queryInfo.pageSize = newSize
      this.getBlogsList()
    },
    handleCurrentChange (newSize) {
      this.queryInfo.pageNum = newSize
      this.getBlogsList()
    },
    // 通过Id删除博客信息
    async removeById (id) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该博客信息, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除！')
      }
      const { data: res } = await this.$http.delete('blog/' + id)
      if (res.code !== 200) {
        this.$router.push('/no')
        return this.$message.error('没有权限，删除博客信息失败！')
      }
      this.$message.success('删除博客信息成功！')
      this.getBlogsList()
    },
    goAddPage () {
      const id = window.sessionStorage.getItem('id')
      console.log(id);
      if (id == 3) {
        this.$router.push('/no')
        return this.$message.error('没有权限，无法进行添加！')
      }
      this.$router.push('/blog/add')
    },
    // 展示分配类型的对话框
    async setType(blogInfo) {
      this.blogInfo = blogInfo
      this.types.typeId = blogInfo.typeId
      this.types.typeName = blogInfo.typeName
      // 在展示对话框之前，获取所有类型的列表
      const { data: res } = await this.$http.get('types')
      if (res.code !== 200) {
        return this.$message.error('获取分类列表失败！')
      }

      this.typeList = res.data

      this.setTypeDialogVisible = true
    },
    // 点击按钮，分配类型
    async saveTypeInfo() {
      if (!this.selectedTypeId) {
        return this.$message.error('请选择要分配的类型！')
      }

      const { data: res } = await this.$http.get(
        `blog/${this.blogInfo.id}/${this.selectedTypeId}`
        )

      if (res.code !== 200) {
        return this.$message.error('更新类型失败！')
      }

      this.$message.success('更新类型成功！')
      this.getBlogsList()
      this.setTypeDialogVisible = false
    },
    // 监听分配类型对话框的关闭事件
    setTypeDialogClosed() {
      this.selectedTypeId = ''
      this.blogInfo = {}
    },
    // 1、进入元素
    mouseoverImg (data) {
      this.imgPath = data.firstPicture
      this.imgTitle = data.title
      this.dialogFormVisible = true
    }
  }
}
</script>

<style lang="less" scoped>
</style>
