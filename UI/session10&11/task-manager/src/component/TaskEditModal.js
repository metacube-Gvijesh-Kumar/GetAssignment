import TaskCardEdit from "./TaskCardEdit";

const TaskEditModal = (props) => {
     
    return <div className="modal fade" id="taskEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="taskEditModalLabel" aria-hidden="true">
        <div className="modal-dialog">
            <div className="modal-content">
                <div className="modal-header">
                    <h4 className="text-info-emphasis">Edit task</h4>
                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div className="modal-body">
                    <TaskCardEdit></TaskCardEdit>
                </div>
            </div>
        </div>
    </div>
}

export default TaskEditModal;