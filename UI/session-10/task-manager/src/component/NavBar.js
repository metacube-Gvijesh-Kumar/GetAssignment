import TaskCardEdit from "./TaskCardEdit";

const NavBar = (props) => {
    return (<>
        <nav className="p-4 d-flex justify-content-between border border-bottom position-sticky w-100 bg-white">
            <p className="mb-0 text-info p-2 rounded fw-bolder">Task tracker</p>
            <button class="p-2 rounded border-0 btn btn-info text-white fw-bolder" data-bs-toggle="modal" data-bs-target="#staticBackdrop">create</button>
        </nav>

        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <TaskCardEdit></TaskCardEdit>
                    </div>
                </div>
            </div>
        </div>

    </>);
}

export default NavBar;