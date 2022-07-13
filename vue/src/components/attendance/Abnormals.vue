<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>出勤异常管理</el-breadcrumb-item>
      <el-breadcrumb-item>出勤列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getAbnormalsList">
            <el-button slot="append" icon="el-icon-search" @click="getAbnormalsList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="goAddPage">添加出勤异常信息</el-button>
        </el-col>
      </el-row>
      <!-- 表格数据 -->
      <el-table :data="abnormalsList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="姓名" prop="name"></el-table-column>
        <el-table-column label="类型" prop="type"></el-table-column>
        <el-table-column label="时长" prop="length"></el-table-column>
        <el-table-column label="登记时间" prop="date">
          <template slot-scope="scope">{{scope.row.date | dataFormat }}</template>
        </el-table-column>
        <el-table-column label="登记人" prop="admin.urealname"></el-table-column>
        <el-table-column label="操作" width="130px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="update()"></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeById(scope.row.id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页区域 -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        background
      ></el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {
      queryInfo: {
        query: '',
        pagenum: 1,
        pagesize: 10
      },
      // 出勤异常信息列表
      abnormalsList: [],
      // 出勤异常信息总数
      total: 0
    }
  },
  created () {
    this.getAbnormalsList()
  },
  methods: {
    update() {
      return this.$message.error('你太丑！无法修改出勤异常信息列表！')
    },
    // 根据分页获取对应的出勤异常信息列表
    async getAbnormalsList () {
      const { data: res } = await this.$http.get('abnormal', {
        params: this.queryInfo
      })
      console.log(res);
      if (res.meta.status !== 200) {
        return this.$message.error('获取出勤异常信息列表失败！')
      }
      this.abnormalsList = res.data.attendances
      //   console.log(this.abnormalsList)
      this.total = res.data.total
    },
    handleSizeChange (newSize) {
      this.queryInfo.pagesize = newSize
      this.getAbnormalsList()
    },
    handleCurrentChange (newSize) {
      this.queryInfo.pagenum = newSize
      this.getAbnormalsList()
    },
    // 通过Id删除出勤异常信息
    async removeById (id) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该出勤异常信息, 是否继续?',
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
      const { data: res } = await this.$http.delete('abnormal/' + id +
       "/" + window.sessionStorage.getItem('id'))
      if (res.meta.status !== 200) {
        this.$router.push('/no')
        return this.$message.error('没有权限，删除出勤异常信息失败！')
      }
      this.$message.success('删除出勤异常信息成功！')
      this.getAbnormalsList()
    },
    goAddPage () {
      const id = window.sessionStorage.getItem('id')
      console.log(id);
      if (id == 3) {
        this.$router.push('/no')
        return this.$message.error('没有权限，无法进行添加！')
      }
      this.$router.push('/abnormals/add')
    }
  },
  filters:{
    dataFormat(originVal) {
      const dt = new Date(originVal)

      const y = dt.getFullYear()
      const m = (dt.getMonth() + 1 + '').padStart(2, '0')
      const d = (dt.getDate() + '').padStart(2, '0')

      const hh = (dt.getHours() + '').padStart(2, '0')
      const mm = (dt.getMinutes() + '').padStart(2, '0')
      const ss = (dt.getSeconds() + '').padStart(2, '0')

      return `${y}-${m}-${d} ${hh}:${mm}:${ss}`
    }
  }
}
</script>

<style lang="less" scoped>
</style>
