import request from '@/utils/request'

export default {
  // 分页查询游戏列表
  getGameList(searchModel) {
    return request({
      url: '/game/list',
      method: 'get',
      params: {
        gameId: searchModel.gameId,
        gameName: searchModel.gameName,
        pageNo: searchModel.pageNo,
        pageSize: searchModel.pageSize
      }
    })
  },
  // 新增
  addGame(game) {
    return request({
      url: '/game',
      method: 'post',
      data: game
    })
  },
  // 修改
  updateGame(game) {
    return request({
      url: '/game',
      method: 'put',
      data: game
    })
  },
  // 保存游戏数据
  saveGame(game) {
    if (game.gameId == null) {
      return this.addGame(game)
    }
    return this.updateGame(game)
  },
  // 根据id查询
  getGameById(id) {
    return request({
      url: `/game/${id}`,
      method: 'get'
    })
  },
  // 根据id删除
  deleteGameById(id) {
    return request({
      url: `/game/${id}`,
      method: 'delete'
    })
  }

}
