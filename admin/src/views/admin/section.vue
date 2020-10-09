<template>
    <div>
        <p>
            <button class="btn btn-white btn-default btn-round" v-on:click="add()">
                <i class="ace-icon fa fa-edit red2"></i>
                新增
            </button>&nbsp;&nbsp;
            <button class="btn btn-white btn-default btn-round" v-on:click="list(1)">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
        </p>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                                    <th>id</th>
                    <th>标题</th>
                    <th>课程</th>
                    <th>大章</th>
                    <th>视频</th>
                    <th>时长</th>
                    <th>收费</th>
                    <th>顺序</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>vod</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="section in sections":key="section">
                    <td> {{section.id}}</td>
                    <td> {{section.title}}</td>
                    <td> {{section.courseId}}</td>
                    <td> {{section.chapterId}}</td>
                    <td> {{section.video}}</td>
                    <td> {{section.time}}</td>
                    <td> {{section.charge}}</td>
                    <td> {{section.sort}}</td>
                    <td> {{section.createdAt}}</td>
                    <td> {{section.updatedAt}}</td>
                    <td> {{section.vod}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(section)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(section.id)">
                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                        </button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <!--引入模态框-->
        <div class="modal fade" tabindex="-1" id="form-modal" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">id</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">标题</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.title">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">课程</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.courseId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">大章</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.chapterId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">视频</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.video">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">时长</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.time">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">收费</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.charge">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">顺序</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.sort">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">创建时间</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.createdAt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">修改时间</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.updatedAt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">vod</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="section.vod">
                                    </div>
                                </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" v-on:click="save()" class="btn btn-primary">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <pagination v-bind:list="list" ref="pagination"></pagination>
    </div>
</template>
<script>
    import Pagination from "../../components/pagination";
    export default {
        components: {Pagination},
        mounted:function(){
            let _this=this;
            _this.list(1);
            this.$parent.activeSidebar("business-section-sidebar");
        },
        data: function() {
            return {
                section:{},
                sections: [],
            }
        },
        name: 'section',
        methods:{
            add(){
                let _this=this;
                $("#form-modal").modal("show");
            },
            edit(section){
                let _this=this;
                _this.section = section;
                $("#form-modal").modal("show");
            },
            /*首页列表查询*/
            list(page){
                let _this=this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/section/list",
                    {page:page,
                        size:_this.$refs.pagination.size}).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    _this.sections=resp.content.list;
                    _this.$refs.pagination.render(page,resp.content.total);
                })
            },
            /*保存更新*/
            save(){
                let _this=this;
                if(1 != 1

                    || !Validator.require(_this.section.Title,"标题")
                    || Validator.length(_this.section.Title,"标题",1,50)
                    || Validator.length(_this.section.CourseId,"课程",1,0)
                ){
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/section/save",
                    _this.section).then((response)=>{
                    let resp = response.data;
                    if(resp.success){
                        Loading.hide()
                        $("#form-modal").modal("hide");
                        _this.list(1);
                        Toast.success("保存成功");
                    }
                    else{
                        Toast.warning(resp.message)
                    }
                })
            },
            /*根据id删除数据*/
            del(id){
                let _this=this;
                Swal.fire({
                    title: '确定删除?',
                    text: "删除后不能恢复!",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '删除!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        _this.$ajax.delete(process.env.VUE_APP_SERVER+"/business/admin/section/delete/"+id,
                        ).then((response)=>{
                            let resp = response.data;
                            if(resp.success){
                                Loading.show();
                                _this.list(1);
                                Loading.hide();
                                toast.success("删除成功");
                            }
                        })

                    }
                })

            }
        }
    }
</script>