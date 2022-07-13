<template>
  <div>
    <!-- 面包屑导航区 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 卡片视图 -->
    <el-card>
      <!-- 添加角色按钮 -->
      <el-row>
        <el-col>
          <el-button type="primary" @click="AddRoleDialogVisible=true">添加角色</el-button>
        </el-col>
      </el-row>
      <!-- 角色列表 -->
      <el-table :data="rolesList" border stripe>
         <!-- 展开列 -->
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-row
              :class="['bdbottom', i1 === 0 ? 'bdtop' : '', 'vcenter']"
              v-for="(item1, i1) in scope.row.children"
              :key="item1.id"
            >
              <!-- 一级权限 -->
              <el-col :span="5">
                <el-tag closable @close="removeRightById(scope.row, item1.id)">{{ item1.authName}}</el-tag>
                <i class="el-icon-caret-right"></i>
              </el-col>
              <!-- 二级和三级 -->
              <el-col :span="19">
                <!-- 通过for循环 渲染二级权限 -->
                <el-row
                  :class="[i2 === 0 ? '' : 'bdtop', 'vcenter']"
                  v-for="(item2, i2) in item1.children"
                  :key="item2.id"
                >
                  <el-col :span="6 ">
                    <el-tag
                      type="success"
                      closable
                      @close="removeRightById(scope.row, item2.id)"
                    >{{ item2.authName }}</el-tag>
                    <i class="el-icon-caret-right"></i>
                  </el-col>
                  <el-col :span="18">
                    <el-tag
                      type="warning"
                      v-for="(item3) in item2.children"
                      :key="item3.id"
                      closable
                      @close="removeRightById(scope.row, item3.id)"
                    >{{ item3.authName}}</el-tag>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </template>
        </el-table-column>
        <!-- 索引列 -->
        <el-table-column type="index" label="#"></el-table-column>
        <el-table-column label="角色名称" prop="roleName"></el-table-column>
        <el-table-column label="角色描述" prop="roleDesc"></el-table-column>
        <el-table-column label="操作" width="300px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)">编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeRoleById(scope.row.id)">删除</el-button>
            <el-button
              type="warning"
              icon="el-icon-setting"
              size="mini"
              @click="setRole(scope.row)"
            >分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 分配角色的对话框 -->
    <el-dialog title="分配权限" :visible.sync="setRightsDialogVisible" width="50%" @close="setRightsDialogClosed">
      <div>
        <p>当前的角色：{{roles.roleName}}</p>
        <p>分配新角色：
          <el-select v-model="roles.rightsId" placeholder="请选择">
            <el-option label="一级" value="1">
            </el-option>
            <el-option label="二级" value="2">
            </el-option>
            <el-option label="三级" value="3">
            </el-option>
          </el-select>
        </p>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="setRightsDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleInfo">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 添加角色对话框 -->
    <el-dialog title="添加角色" :visible.sync="AddRoleDialogVisible" width="40%" @close="addRoleDialogClosed">
      <el-form
        :model="addRoleForm"
        ref="addRoleFormRef"
        :rules="addRoleFormRules"
        label-width="100px"
      >
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="addRoleForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="addRoleForm.roleDesc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="AddRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoles">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑角色对话框 -->
    <el-dialog title="编辑角色" :visible.sync="editRoleDialogVisible" width="40%" @close="editRoleDialogClosed">
      <el-form
        :model="editRoleForm"
        ref="editRoleFormRef"
        :rules="editRoleFormRules"
        label-width="100px"
      >
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="editRoleForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="editRoleForm.roleDesc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editRoles">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 所有角色列表
      rolesList: [],
      // 分配权限框
      setRightDialogVisible: false,
      // 权限列表
      rightsList: [],
      //  树形控件的属性绑定对象
      treeProps: {
        label: 'authName',
        children: 'children'
      },
      //   默认选中节点ID值
      defKeys: [],
      //   添加用户对话框
      AddRoleDialogVisible: false,
      // 添加角色表单
      addRoleForm: {},
      //   添加角色表单验证
      addRoleFormRules: {
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ],
        roleDesc: [
          { required: true, message: '请输入角色描述', trigger: 'blur' }
        ]
      },
      //   编辑角色信息
      editRoleForm: {},
      editRoleDialogVisible: false,
      editRoleFormRules: {
        roleName: [
          { required: true, message: '请输入角色名称', trigger: 'blur' }
        ],
        roleDesc: [
          { required: true, message: '请输入角色描述', trigger: 'blur' }
        ]
      },
      //   当前即将分配权限的Id
      roleId: 0,
        // 控制分配角色对话框的显示与隐藏
      setRightsDialogVisible: false,
      roles:{},
      selectedRoleId:''
    }
  },
  created () {
    this.getRolesList()
  },
  methods: {
    async getRolesList () {
      const id = window.sessionStorage.getItem('id');
      console.log(id);
      const { data: res } = await this.$http.get('roles')
      if (res.code !== 200) {
        this.$router.push('/no')
        return this.$message.error('没有权限，获取列表失败！')
      }
      this.rolesList = res.data
      console.log(res);
    },
    // 根据ID删除对应的权限
    async removeRightById (role, rightId) {
      // 弹框提示 删除
      const confirmResult = await this.$confirm(
        '此操作将永久删除该权限, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      // 点击确定 返回值为：confirm
      // 点击取消 返回值为： cancel
      if (confirmResult !== 'confirm') {
        this.$message.info('已取消权限删除')
      }
      const { data: res } = await this.$http.delete(
        `roles/${role.id}/rights/${rightId}`
      )
      if (res.code !== 200) {
        return this.$message.error('删除权限失败！')
      }
      this.rolesList = res.data
      //   不建议使用
      this.getRolesList()
    },
    // 分配权限
    async showSetRightDialog (role) {
      this.roleId = role.id
      // 获取角色的所有权限
      const { data: res } = await this.$http.get('rights/tree/1')
      if (res.code !== 200) {
        return this.$message.error('获取权限数据失败！')
      }
      //   获取权限树
      this.rightsList = res.data
      console.log(res);
      //   console.log(res)
      //   递归获取三级节点的id
      this.getLeafkeys(role, this.defKeys)

      this.setRightDialogVisible = true
    },
    // 通过递归 获取角色下三级权限的 id, 并保存到defKeys数组
    getLeafkeys (node, arr) {
      // 没有children属性，则是三级节点
      if (!node.children) {
        return arr.push(node.id)
      }
      node.children.forEach(item => this.getLeafkeys(item, arr))
    },
    // 权限对话框关闭事件
    setRightDialogClosed () {
      this.rightsList = []
    },
    // 添加角色对话框的关闭
    addRoleDialogClosed () {
      this.$refs.addRoleFormRef.resetFields()
    },
    // 监听修改用户对话框的关闭事件
    editRoleDialogClosed() {
      this.$refs.editRoleFormRef.resetFields()
    },
     // 展示分配角色的对话框
    async setRole(roles) {
      console.log(roles);
      this.roles = roles
      this.setRightsDialogVisible = true
    },
    // 点击按钮，分配角色
    async saveRoleInfo() {
      const { data: res } = await this.$http.get(
        `roles1/${this.roles.id}/${this.roles.rightsId}`
        )

      if (res.code !== 200) {
        return this.$message.error(res.mes)
      }

      this.$message.success('更新权限成功！')
      this.getRolesList()
      this.setRightsDialogVisible = false
    },
    // 监听分配角色对话框的关闭事件
    setRightsDialogClosed() {
      this.selectedRoleId = ''
    },
    // 添加角色
    addRoles () {
      this.$refs.addRoleFormRef.validate(async valid => {
        if (!valid) return
        const { data: res } = await this.$http.post('roles', this.addRoleForm)
        if (res.code !== 200) {
          this.$message.error('添加角色失败！')
        }
        this.$message.success('添加角色成功！')
        this.AddRoleDialogVisible = false
        this.getRolesList()
      })
    },
    // 删除角色
    async removeRoleById (id) {
      const confirmResult = await this.$confirm(
        '此操作将永久删除该角色, 是否继续?',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).catch(err => err)
      if (confirmResult !== 'confirm') {
        return this.$message.info('已取消删除')
      }
      const { data: res } = await this.$http.delete('roles/' + id)
      if (res.code !== 200) return this.$message.error('角色被使用，删除失败！')
      this.$message.success('删除角色成功！')
      this.getRolesList()
    },
    // 编辑角色
    async showEditDialog (id) {
      const { data: res } = await this.$http.get('roles/' + id)
      if (res.code !== 200) return this.$message.error('查询角色信息失败！')
      this.editRoleForm = res.data
      this.editRoleDialogVisible = true
    },
    editRoles () {
      this.$refs.editRoleFormRef.validate(async valid => {
        // console.log(valid)
        // 表单预校验失败
        if (!valid) return
        console.log(this.editRoleForm);
        const { data: res } = await this.$http.put(
          'roles',
          {
            id: this.editRoleForm.id,
            roleName: this.editRoleForm.roleName,
            roleDesc: this.editRoleForm.roleDesc
          }
        )
        if (res.code !== 200) {
          this.$message.error('更新角色信息失败！')
        }
        // 隐藏编辑角色对话框
        this.editRoleDialogVisible = false
        this.$message.success('更新角色信息成功！')
        this.getRolesList()
      })
    },
    // 分配权限
    async allotRights (roleId) {
      // 获得当前选中和半选中的Id
      const keys = [
        ...this.$refs.treeRef.getCheckedKeys(),
        ...this.$refs.treeRef.getHalfCheckedKeys()
      ]
      // join() 方法用于把数组中的所有元素放入一个字符串
      const idStr = keys.join(',')
      const { data: res } = await this.$http.post(`roles/${this.roleId}/rights`, { rids: idStr })
      if (res.code !== 200) { return this.$message.error('分配权限失败！') }
      console.log(this.rightsList);
      console.log(this.rolesList);
      this.$message.success('分配权限成功!')
      this.getRolesList()
      this.setRightDialogVisible = false
    }
  }
}
</script>

<style lang="less" scoped>
.el-tag {
  margin: 7px;
}
.bdtop {
  border-top: 1px solid #eee;
}
.bdbottom {
  border-bottom: 1px solid #eee;
}
.vcenter {
  display: flex;
  align-items: center;
}
</style>
