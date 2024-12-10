import './App.css';
import NavBar from './component/NavBar';
import TaskSection from './component/TaskSection';
import 'bootstrap-icons/font/bootstrap-icons.css';

function App() {
  
  return (
    <div className="App">
      <NavBar></NavBar>
      <div className='container-fluid'>
        <div className="row g-3 justify-content-center">
        <TaskSection title='New'></TaskSection>
        <TaskSection title='On going'></TaskSection>
        <TaskSection title='Completed'></TaskSection>
        </div>
      </div>
    </div>
  );
}

export default App;
