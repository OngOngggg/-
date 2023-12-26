<template>
  <div>
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input v-model="searchModel.username" placeholder="姓名" clearable />
          <el-input v-model="searchModel.gamename" placeholder="游戏名" clearable />
          <el-button type="primary" round icon="el-icon-search" @click="getUserGameList">搜索</el-button>
        </el-col>
        <el-col :span="4" align="right">
            <el-button @click="openEditUI(true)" type="primary" icon="el-icon-plus" circle />
        </el-col>
      </el-row>
    </el-card>

    <el-card>
      <el-table :data="userGameList" border style="width: 100%">
        <el-table-column label="#" width="80">
          <template slot-scope="scope">
            {{(searchModel.pageNo-1) * searchModel.pageSize + scope.$index + 1}}
          </template>
        </el-table-column>
        <el-table-column prop="username" label="姓名" width="180" />
        <el-table-column prop="gamename" label="游戏名" width="180" />
        <el-table-column prop="gameHour" label="游戏时长" width="180"/>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button @click="openEditUI(false,scope.row)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button @click="deleteUserGame(scope.row)" type="danger" icon="el-icon-delete" size="mini" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!--分页组件  -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

    <!--用户表单信息 -->
    <el-dialog @close="clearForm" :title=title :visible.sync="dialogFormVisible">
      <el-form :model="userGameForm" ref="userGameFormRef">
        <el-form-item label="用户名" prop="username" :label-width="formLabelWidth">
          <el-select v-model="userGameForm.username" filterable placeholder="请选择">
            <el-option
              v-for="item in userList"
              :key="item.username"
              :label="item.username"
              :value="item.username">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="游戏名" prop="gamename" :label-width="formLabelWidth">
          <el-select v-model="userGameForm.gamename" filterable placeholder="请选择">
            <el-option
              v-for="item in gameList"
              :key="item.gameName"
              :label="item.gameName"
              :value="item.gameName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="游戏时长" :label-width="formLabelWidth">
          <el-input v-model="userGameForm.gameHour" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click ="saveUserGame">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import userGameApi from '@/api/userGameManage'
import gameApi from '@/api/gameManage'
import userApi from '@/api/userManager'
export default {
  data() {
    return {
      userGameForm: {},
      dialogFormVisible: false,
      title: '',
      formLabelWidth: '130px',
      total: 0,
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      gameList: [],
      userGameList: [],
      userList: []
    }
  },
  created() {
    this.getUserGameList()
    this.getGameList()
    this.getUserList()
  },
  methods: {
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getUserGameList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getUserGameList()
    },
    getUserGameList() {
      userGameApi.getUserGameList(this.searchModel).then(response => {
        this.userGameList = response.data.rows
        this.total = response.data.total
      })
    },
    getGameList() {
      gameApi.getGameList({ pageNo: 1, pageSize: 100000 }).then(response => {
        this.gameList = response.data.rows
      })
    },
    getUserList() {
      userApi.getUserList({ pageNo: 1, pageSize: 100000 }).then(response => {
        this.userList = response.data.rows
      })
    },
    deleteUserGame(userGame) {
      this.$confirm(`您确认删除用户 ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userGameApi.deleteUserGameById(userGame.username, userGame.gamename).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.getUserGameList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    clearForm() {
      this.userGameForm = {}
      this.$refs.userGameFormRef.clearValidate()
    },
    openEditUI(judge, userGame) {
      if (judge === false) {
        this.title = '修改'
        this.userGameForm.b_username = userGame.username
        this.$set(this.userGameForm, 'username', userGame.username)
        this.userGameForm.b_gamename = userGame.gamename
        this.$set(this.userGameForm, 'gamename', userGame.gamename)
        this.$set(this.userGameForm, 'gameHour', userGame.gameHour)
      } else {
        this.title = '新增'
      }
      this.userGameForm.judge = judge
      this.dialogFormVisible = true
    },
    saveUserGame() {
      userGameApi.saveUserGame(this.userGameForm).then(response => {
        // 成功提示
        this.$message({
          message: response.message,
          type: 'success'
        })
        // 关闭对话框
        this.dialogFormVisible = false
        // 刷新表格
        this.getUserGameList()
      })
    }
  }
}
</script>

<style scoped>
#search .el-input{
  width:200px;
  margin-right: 10px;
}
</style>
