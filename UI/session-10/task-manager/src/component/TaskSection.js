import TaskCard from "./TaskCard";

const TaskSection = (props)=>{
    const updateCallback = (obj)=>{
        console.log('inparent');
        console.log(obj);
    }
    const deleteTaskCallback = (obj)=>{
        console.log('inparent');
        console.log(obj);
    }
    return <>
        <div className="col-3">
            <div className="bg-body-secondary mt-4 text-center card p-4"> 
                    <h5 className="text-info fw-bold">{props.title}</h5>
                    <div className="d-flex flex-column gap-2 mt-4">
                    <TaskCard deleteTaskCallback={deleteTaskCallback} updateCallback={updateCallback} priority="low" status="new" title="Hello this a task" description="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"></TaskCard>
                    <TaskCard deleteTaskCallback={deleteTaskCallback} updateCallback={updateCallback} priority="medium" status="new" title="Hello this a task" description="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"></TaskCard>
                    <TaskCard deleteTaskCallback={deleteTaskCallback} updateCallback={updateCallback} priority="high" status="new" title="Hello this a task" description="Lorem ipsum "></TaskCard>
                    </div>
            </div>
        </div>
    </>
}

export default TaskSection;