import { useState } from "react";
import './TaskCard.css'

const TaskCardEdit = (props)=>{
    
    const taskUpdater = props.updateCallback;
    const title = props.title;
    const description = props.description;
    const status = props.status;
    const priority =  props.priority;
    
    const save = (e)=>{
        e.preventDefault();
        const formData = new FormData(e.target);
        console.log(formData);
        taskUpdater(e);
    }



    return <>
            <div className="card">
                <div className="card-body">
                    <form onSubmit={(e)=>{save(e)}} className="d-flex flex-column gap-2 align-items-start text-start">
                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis">Title</h6>
                                <input className="w-100 card-text lead fs-6" type="text" defaultValue={title}></input>
                            </label>

                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis" >Description</h6>
                                <textarea className="card-text lead fs-6 w-100" rows='4' defaultValue={description}></textarea>
                            </label>
                            
                            
                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis" >Priority</h6>
                                
                                <select className="w-100" name="priority" defaultValue={priority}>
                                    <option value="low"><span className="badge rounded-pill text-bg-secondary">Low</span></option>
                                    <option value="medium"><span className="badge rounded-pill text-bg-warning">Medium</span></option>
                                    <option value="high"><span className="badge rounded-pill text-bg-danger">High</span></option>
                                </select>
            
                            </label>

                            <label className="w-100">
                                <h6 className="card-title text-info-emphasis" >Status</h6>
                                <select className="w-100" name="status" defaultValue={status}>
                                    <option value="new"><span className="badge rounded-pill text-bg-secondary">New</span></option>
                                    <option value="onGoing"><span className="badge rounded-pill text-bg-warning">On Going</span></option>
                                    <option value="completed"><span className="badge rounded-pill text-bg-danger">Completed</span></option>
                                </select>
                            </label>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button  type="submit" className="task-card-icons ms-auto"><i class="bi bi-floppy-fill"></i></button>
                       </form>
                </div>
            </div>
    </>
}

export default TaskCardEdit;

