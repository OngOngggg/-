<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="18">
          <el-input placeholder="游戏名" v-model="searchModel.gameName" clearable> </el-input>
          <el-button @click="getGameList" type="primary" icon="el-icon-search" round>查询</el-button>
        </el-col>
        <el-col :span="6" align="right">
          <el-button @click="openEditUI(null)" type="primary" icon="el-icon-plus" circle></el-button>
        </el-col>
      </el-row>
    </el-card>

    <!-- 结果列表 -->
    <el-card>
      <el-table :data="gameList" stripe style="width: 100%">
        <el-table-column label="#" width="80">
          <template slot-scope="scope">
            {{(searchModel.pageNo-1) * searchModel.pageSize + scope.$index + 1}}
          </template>
        </el-table-column>
        <el-table-column prop="gameId" label="游戏号" width="180">
        </el-table-column>
        <el-table-column prop="gameName" label="游戏名称" width="180"/>
        <el-table-column   label="操作" width="180">
          <template slot-scope="scope">
            <el-button @click="openEditUI(scope.row.id)" type="primary" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button @click="deleteGame(scope.row)" type="danger" icon="el-icon-delete" circle size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-card>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>

    <!-- 对话框 -->
    <el-dialog @close="clearForm" :title="title" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <el-form :model="gameForm" ref="gameFormRef">
        <el-form-item prop="gameName" label="游戏名称" :label-width="formLabelWidth">
          <el-input v-model="gameForm.gameName" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveGame">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import gameApi from '@/api/gameManage'
export default {
  data() {
    return {
      formLabelWidth: '130px',
      gameForm: {},
      dialogFormVisible: false,
      title: '',
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      gameList: [],
      total: 0
    }
  },
  methods: {
    deleteGame: function(game) {
      this.$confirm(`您确定删除游戏 ${game.gameName} ？`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        gameApi.deleteGameById(game.gameId).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.dialogFormVisible = false
          this.getGameList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    saveGame() {
      // 触发表单验证
      this.$refs.gameFormRef.validate((valid) => {
        if (valid) {
          // 提交保存请求
          gameApi.saveGame(this.gameForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            })
            // 关闭对话框
            this.dialogFormVisible = false
            // 刷新表格数据
            this.getGameList()
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    clearForm() {
      this.gameForm = {}
      this.$refs.gameFormRef.clearValidate()
    },
    openEditUI(id) {
      if (id == null) {
        this.title = '新增游戏'
      } else {
        this.title = '修改游戏'
        gameApi.getGameById(id).then(response => {
          this.gameForm = response.data
        })
      }
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getGameList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getGameList()
    },
    getGameList() {
      gameApi.getGameList(this.searchModel).then(response => {
        this.gameList = response.data.rows
        this.total = response.data.total
      })
    }
  },
  created() {
    this.getGameList()
  }
}
</script>

<style>
#search .el-input {
  width: 200px;
  margin-right: 10px;
}
.el-dialog .el-input{
  width: 85%;
}
</style>
