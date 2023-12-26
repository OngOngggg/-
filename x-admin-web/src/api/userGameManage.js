import request from '@/utils/request'

export default {
  getUserGameList(searchModel) {
    return request({
      url: '/userGame/list',
      method: 'get',
      params: {
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        username: searchModel.username,
        gamename: searchModel.gamename,
        gameHour: searchModel.gameHour
      }
    })
  },
  deleteUserGameById(username, gamename) {
    return request({
      url: `/userGame/delete`,
      method: 'post',
      params: {
        username: username,
        gamename: gamename
      }
    })
  },
  saveUserGame(userGameForm) {
    return request({
      url: '/userGame/save',
      method: 'post',
      params: {
        b_username: userGameForm.b_username,
        username: userGameForm.username,
        b_gamename: userGameForm.b_gamename,
        gamename: userGameForm.gamename,
        gameHour: userGameForm.gameHour,
        judge: userGameForm.judge
      }
    })
  }
}
