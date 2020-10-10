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
                    <th>名称</th>
                    <th>概述</th>
                    <th>时长</th>
                    <th>价格（元）</th>
                    <th>封面</th>
                    <th>级别</th>
                    <th>收费</th>
                    <th>状态</th>
                    <th>报名数</th>
                    <th>顺序</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>讲师</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="course in courses":key="course">
                    <td> {{course.id}}</td>
                    <td> {{course.name}}</td>
                    <td> {{course.summary}}</td>
                    <td> {{course.time}}</td>
                    <td> {{course.price}}</td>
                    <td> {{course.image}}</td>
                    <td> {{COURSE_LEVEL | optionKV(course.level)}}</td>
                    <td> {{COURSE_CHARGE | optionKV(course.charge)}}</td>
                    <td> {{COURSE_STATUS| optionKV(course.status)}}</td>
                    <td> {{course.enroll}}</td>
                    <td> {{course.sort}}</td>
                    <td> {{course.createdAt}}</td>
                    <td> {{course.updatedAt}}</td>
                    <td> {{course.teacherId}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-info" v-on:click="edit(course)">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                        </button>

                        <button class="btn btn-xs btn-danger" v-on:click="del(course.id)">
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
                                        <input type="text" class="form-control" v-model="course.id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">名称</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">概述</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.summary">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">时长</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.time">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">价格（元）</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.price">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">封面</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.image">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">级别</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" v-model="course.level">
                                            <option v-for="o in COURSE_LEVEL" v-bind:value="o.key">
                                                {{o.value}}
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">收费</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" v-model="course.charge">
                                            <option v-for="o in COURSE_CHARGE" v-bind:value="o.key">
                                                {{o.value}}
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">状态</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" v-model="course.status">
                                            <option v-for="o in COURSE_STATUS" v-bind:value="o.key">
                                                {{o.value}}
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">报名数</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.enroll">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">顺序</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.sort">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">创建时间</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.createdAt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">修改时间</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.updatedAt">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">讲师</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" v-model="course.teacherId">
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
    import Section from "./section";
    export default {
        components: {Section, Pagination},
        mounted:function(){
            let _this=this;
            _this.list(1);
            this.$parent.activeSidebar("business-course-sidebar");
        },
        data: function() {
            return {
                course:{},
                courses: [],
                COURSE_CHARGE:COURSE_CHARGE,
                COURSE_STATUS:COURSE_STATUS,
                COURSE_LEVEL:COURSE_LEVEL,
            }
        },

        name: 'business-course',
        methods:{
            add(){
                let _this=this;
                $("#form-modal").modal("show");
            },
            edit(course){
                let _this=this;
                _this.course = course;
                $("#form-modal").modal("show");
            },
            /*首页列表查询*/
            list(page){
                let _this=this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/course/list",
                    {page:page,
                        size:_this.$refs.pagination.size}).then((response)=>{
                    Loading.hide();
                    let resp = response.data;
                    _this.courses=resp.content.list;
                    _this.$refs.pagination.render(page,resp.content.total);
                })
            },
            /*保存更新*/
            save(){
                let _this=this;
                if(1 != 1
                    || !Validator.require(_this.course.name,"名称")
                    || !Validator.length(_this.course.name,"名称",1,50)
                    || !Validator.length(_this.course.summary,"概述",1,2000)
                    || !Validator.length(_this.course.image,"封面",1,100)
                ){
                    return;
                }

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER+"/business/admin/course/save",
                    _this.course).then((response)=>{
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
                        _this.$ajax.delete(process.env.VUE_APP_SERVER+"/business/admin/course/delete/"+id,
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