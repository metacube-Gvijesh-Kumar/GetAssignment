import { useState } from "react";
import TaskCardEdit from "./TaskCardEdit";
import './TaskCard.css'

const TaskCard = (props)=>{

    const updateCallback = props.updateCallback;

    const title = props.title;
    const description = props.description;
    const status = props.status;
    const priority =  props.priority;
    const deleteTaskCallback = props.deleteTaskCallback; 

    let badgeStyle='badge rounded-pill ';
    if(priority==='low')
        badgeStyle+='text-bg-secondary';
    else if(priority==='medium')
        badgeStyle+='text-bg-warning';
    else if(priority==='high')
        badgeStyle+='text-bg-danger';

    const [open,setOpen] = useState(false);
    const [edit,setEdit] = useState(false);


    const deleteTask =(e)=>{
        //delete task method callback might be coming from the parent
        deleteTaskCallback(e);
    }

return <>
        {
        !edit?
            <div className="card">
                <div className="card-body text-start">
                    <h6 className="card-title text-info-emphasis">{title}</h6>
                    <p className="card-text lead fs-6">{open || description.length<50?description:description.substring(0,50)+'...'}</p>
                    
                    <div className="d-flex justify-content-between align-items-center">
                        <span className={badgeStyle}>{priority}</span>
                        <span>
                            {description.length>50?<button onClick={(e)=>{setOpen(!open)}} className="task-card-icons">{open?<i class="bi bi-eye-slash-fill"></i>:<i class="bi bi-eye-fill"></i>}</button>:<></>}
                            <button onClick={(e)=>{setEdit(true)}} className="task-card-icons"><i class="bi bi-pencil-fill"></i></button>
                            {status!='completed'?<button onClick={(e)=>{deleteTask(e)}} className="task-card-icons"><i class="bi bi-trash-fill"></i></button>:<></>}
                        </span>
                    </div>
                </div>
            </div>
        :
        <TaskCardEdit  updateCallback={updateCallback} priority={priority} status={status} title={title} description={description}></TaskCardEdit>
        }
    </>
}

export default TaskCard;