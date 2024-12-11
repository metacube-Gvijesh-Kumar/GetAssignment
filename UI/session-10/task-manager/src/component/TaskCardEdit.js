import { useState,useContext, useEffect } from "react";
import './TaskCard.css'
import TaskContext from "../TaskContext";
import TasksContext from "../TasksContext";

const TaskCardEdit = (props)=>{
    
    const [taskUnderEdit, setTaskUnderEdit] = useContext(TaskContext);
    const [tasksDetails,setTasksDetails] = useContext(TasksContext);

    const {title,description,status,priority,id} = taskUnderEdit;


    const save = (e)=>{
        e.preventDefault();
        
        setTaskUnderEdit({});
        const formData = new FormData(e.target);
        const taskToSave ={};
        for (let [key, value] of formData) {
            taskToSave[key]=value;
        }

        const newTaskList = tasksDetails.tasks.filter((t)=>t.id!=id);
        let nextTaskId = tasksDetails.nextTaskId;

        if(!id){
            newTaskList.push({...taskToSave,id:nextTaskId});
            nextTaskId++;
        }
        else
            newTaskList.push({...taskToSave,id:id});

        const newTaskDetails = {tasks:newTaskList,nextTaskId:nextTaskId};
        setTasksDetails(newTaskDetails);
    }

    return <>
            <div className="card border-0">
                <div className="card-body">
                    <form onSubmit={(e)=>{save(e)}} className="d-flex flex-column gap-3 align-items-start text-start">
                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis">Title</h6>
                                <input required minLength={1} name="title" className="w-100 card-text lead fs-6" type="text" defaultValue={title}></input>
                            </label>

                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis" >Description</h6>
                                <textarea required minLength={10} name="description" className="card-text lead fs-6 w-100" rows='4' defaultValue={description}></textarea>
                            </label>
                            
                            
                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis" >Priority</h6>
                                
                                <select className="w-100" name="priority" >
                                    <option selected={priority=='low'?true:false} value="low"><span className="badge rounded-pill text-bg-secondary">Low</span></option>
                                    <option selected={priority=='medium'?true:false} value="medium"><span className="badge rounded-pill text-bg-warning">Medium</span></option>
                                    <option selected={priority=='high'?true:false} value="high"><span className="badge rounded-pill text-bg-danger">High</span></option>
                                </select>
            
                            </label>

                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis" >Status</h6>
                                <select className="w-100" name="status">
                                    <option selected={status=='new'?true:false} value="new"><span className="badge rounded-pill text-bg-secondary">New</span></option>
                                    {taskUnderEdit.id?
                                    <>
                                    <option selected={status=='onGoing'?true:false} value="onGoing"><span className="badge rounded-pill text-bg-warning">On Going</span></option>
                                    <option selected={status=='completed'?true:false} value="completed"><span className="badge rounded-pill text-bg-danger">Completed</span></option>
                                    </>
                                    :<></>}
                                </select>
                            </label>
                            <button data-bs-dismiss="modal"  type="submit" className=" text-info-emphasis task-card-icons ms-auto"><i class="bi bi-floppy-fill"></i> SAVE</button>
                       </form>
                </div>
            </div>
    </>
}

export default TaskCardEdit;

