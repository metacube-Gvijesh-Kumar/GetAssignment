import TaskCardEdit from "./TaskCardEdit";
import TaskContext from "../TaskContext";
import { useContext } from "react";

const NavBar = (props) => {

    const [taskUnderEdit, setTaskUnderEdit] = useContext(TaskContext);

    const handleModalClose = (e)=>{
        setTaskUnderEdit({});
    }

    
    return (<>
        <nav className="p-4 d-flex justify-content-between border border-bottom position-sticky w-100 bg-white">
            <p className="mb-0 text-info p-2 rounded fw-bolder">Task tracker</p>
            <button className="p-2 rounded border-0 btn btn-info text-white fw-bolder" data-bs-toggle="modal" data-bs-target="#taskSaveModal">create</button>
        </nav>

        <div className="modal fade" id="taskSaveModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="taskSaveModalLabel" aria-hidden="true">
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h4 className="text-info-emphasis">Create a task</h4>
                        <button type="button" onClick={(e)=>{handleModalClose()}}   className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        <TaskCardEdit></TaskCardEdit>
                    </div>
                </div>
            </div>
        </div>

    </>);
}

export default NavBar;