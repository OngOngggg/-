<template>
  <div>
    <el-card>
      <el-select v-model="gamename" filterable placeholder="请选择">
        <el-option
          v-for="item in gameList"
          :key="item.gameName"
          :label="item.gameName"
          :value="item.gameName">
        </el-option>
      </el-select>
      <el-button type="primary" round icon="el-icon-search" @click="findPercent(gamename)">搜索</el-button>
    </el-card>
    <el-card>
      <el-progress type="circle" :percentage="percent_of_game" :width="circleWidth"></el-progress>
    </el-card>
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
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      userList: [],
      gameList: [],
      userGameList: [],
      gamename: '',
      userNum: 0,
      gameNum: '0',
      percent_of_game: 0,
      circleWidth: 200
    }
  },
  created() {
    this.getUserGameList()
    this.getGameList()
    this.getUserList()
  },
  methods: {
    getUserGameList() {
      userGameApi.getUserGameList({ pageNo: 1, pageSize: 10000 }).then(response => {
        this.userGameList = response.data.rows
        this.total = response.data.total
      })
    },
    getUserList() {
      userApi.getUserList({ pageNo: 1, pageSize: 10000 }).then(response => {
        this.userList = response.data.rows
      })
    },
    getGameList() {
      gameApi.getGameList({ pageNo: 1, pageSize: 10000 }).then(response => {
        this.gameList = response.data.rows
      })
    },
    findPercent(gamename) {
      this.userNum = this.userList.length
      const count = this.userGameList.filter(item => item.gamename === this.gamename).length
      this.percent_of_game = (100 * count / this.userNum).toFixed(2)
    }
  }
}
</script>

<style scoped>

</style>
