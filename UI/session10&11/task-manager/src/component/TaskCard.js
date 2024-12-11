import { useContext, useState } from "react";
import './TaskCard.css'
import TaskContext from "../TaskContext";

const TaskCard = (props) => {

    const task = props.task;

    const title = task.title;
    const description = task.description;
    const status = task.status;
    const priority = task.priority;
    const deleteTaskCallback = task.deleteTaskCallback;
    const [taskUnderEdit, setTaskUnderEdit] = useContext(TaskContext);
    
    let badgeStyle = 'badge rounded-pill ';
    if (priority === 'low')
        badgeStyle += 'text-bg-secondary';
    else if (priority === 'medium')
        badgeStyle += 'text-bg-warning';
    else if (priority === 'high')
        badgeStyle += 'text-bg-danger';

    const [open, setOpen] = useState(false);
  
    const handleEdit  = (e)=>{
       
        setTaskUnderEdit(...task);
    }   

    const deleteTask = (e) => {
        //delete task method callback might be coming from the parent
        deleteTaskCallback(e);
    }

    return <>
       
                <div className="card">
                    <div className="card-body text-start">
                        <h6 className="card-title text-info-emphasis">{title}</h6>
                        <p className="card-text lead fs-6">{open || description.length < 50 ? description : description.substring(0, 50) + '...'}</p>

                        <div className="d-flex justify-content-between align-items-center">
                            <span className={badgeStyle}>{priority}</span>
                            <span>
                                {description.length > 50 ? <button onClick={(e) => { setOpen(!open) }} className="task-card-icons">{open ? <i class="text-info-emphasis bi bi-eye-slash-fill"></i> : <i class="text-info-emphasis bi bi-eye-fill"></i>}</button> : <></>}
                                
                                <button onClick={(e)=>{handleEdit(e)}} className="task-card-icons" data-bs-toggle="modal" data-bs-target="#taskEditModal"><i class="text-info-emphasis bi bi-pencil-fill"></i></button>
                                {status != 'completed' ? <button onClick={(e) => { deleteTask(e) }} className="task-card-icons"><i class="text-info-emphasis bi bi-trash-fill"></i></button> : <></>}
                            </span>
                        </div>
                    </div>
                </div>         
    </>
}

export default TaskCard;