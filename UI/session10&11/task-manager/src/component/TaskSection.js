import TaskCard from "./TaskCard";

const TaskSection = (props)=>{

    const tasks = props.tasks;

    return <>
        <div className="col-3">
            <div className="bg-body-secondary mt-4 text-center card p-4"> 
                    <h5 className="text-info fw-bold">{props.title}</h5>
                    <div className="d-flex flex-column gap-2 mt-4">
                    {tasks.map((task)=>{
                        return <TaskCard key={task.id} task={task}></TaskCard>
                    })}
                    </div>
            </div>
        </div>
    </>
}

export default TaskSection;