import { useContext, useState } from "react";
import './TaskCard.css'
import TaskContext from "../TaskContext";
import TasksContext from "../TasksContext";

const TaskCard = (props) => {

    const task = props.task;

    const title = task.title;
    const description = task.description;
    const status = task.status;
    const priority = task.priority;
    const creationDate =task.creationDate;
    const completionDate =task.completionDate;

    const [taskUnderEdit, setTaskUnderEdit] = useContext(TaskContext);
    const [tasksDetails,setTasksDetails] = useContext(TasksContext);

    let badgeStyle = 'badge rounded-pill ';
    if (priority === 'low')
        badgeStyle += 'text-bg-secondary';
    else if (priority === 'medium')
        badgeStyle += 'text-bg-warning';
    else if (priority === 'high')
        badgeStyle += 'text-bg-danger';

    const [open, setOpen] = useState(false);
    const [viewTime, setViewTime] = useState(false);

    const handleEdit  = (e)=>{
        setTaskUnderEdit({...task});
    }   

    const deleteTask = (e) => {
        const newTaskList = tasksDetails.tasks.filter((t)=>t.id!=task.id);
        setTasksDetails({...tasksDetails,tasks: newTaskList});
    }
    //rgb(213,212,212) grey
    //rgb(240 208 208) red
    //rgb(247 247 200) yellow
    
    const bgMap = new Map();

    bgMap.set('low','rgb(213,212,212)');
    bgMap.set('medium','rgb(247 247 200)');
    bgMap.set('high','rgb(240 208 208)');

    const backGroundStyle ={
        backgroundColor:bgMap.get(task.priority)
    }

    return <>
       
                <div style={backGroundStyle} className="card">
                    <div className="card-body text-start">
                        <h6 className="card-title text-info-emphasis">{title}</h6>
                        <p className="card-text lead fs-6">{open || description.length < 50 ? description : description.substring(0, 50) + '...'}</p>
                        {viewTime?
                        <>
                        <p className="card-text lead fs-6">Creation Date: {creationDate}</p>
                        {completionDate?<p className="card-text lead fs-6">CompletionDate:{completionDate}</p>:<></>}
                        </>
                        :<></>}
                        <div className="d-flex justify-content-between align-items-center">
                            <span className={badgeStyle}>{priority}</span>
                            <span>
                                <button onClick={(e) => { setViewTime(!viewTime)}} className="task-card-icons"><i class="text-info-emphasis bi bi-alarm-fill"></i></button>
                                {description.length > 50 ? <button onClick={(e) => { setOpen(!open) }} className="task-card-icons">{open ? <i class="text-info-emphasis bi bi-eye-slash-fill"></i> : <i class="text-info-emphasis bi bi-eye-fill"></i>}</button> : <></>}
                                {status != 'completed' ?<button onClick={(e)=>{handleEdit(e)}} className="task-card-icons" data-bs-toggle="modal" data-bs-target="#taskEditModal"><i class="text-info-emphasis bi bi-pencil-fill"></i></button>:<></>}
                                <button onClick={(e) => { deleteTask(e) }} className="task-card-icons"><i class="text-info-emphasis bi bi-trash-fill"></i></button>
                            </span>
                        </div>
                    </div>
                </div>         
    </>
}

export default TaskCard;