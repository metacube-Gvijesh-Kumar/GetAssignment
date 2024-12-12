import './App.css';
import NavBar from './component/NavBar';
import TaskSection from './component/TaskSection';
import 'bootstrap-icons/font/bootstrap-icons.css';
import TaskEditModal from './component/TaskEditModal'
import { useContext, useState } from 'react';
import TaskContext from './TaskContext';
import TasksContext from './TasksContext';

function App() {

  const nextTaskIdInit = 18;
  const tasksInit = [
    {
      id: 1,
      title: 'Finalize Project Report',
      description: 'Complete the final project report.',
      status: 'completed',
      priority: 'high'
    },
    {
      id: 2,
      title: 'Prepare Presentation for Stakeholders',
      description: 'Create a comprehensive presentation that highlights the project milestones, challenges faced, and future plans for the upcoming stakeholder meeting.',
      status: 'onGoing',
      priority: 'medium'
    },
    {
      id: 3,
      title: 'Code Review for Feature Implementation',
      description: 'Conduct a detailed review of the newly implemented features to ensure they meet the project requirements and coding standards.',
      status: 'new',
      priority: 'low'
    },
    {
      id: 4,
      title: 'Send Invitations for Team Building Event',
      description: 'Draft and send out invitations for the upcoming team-building event, including all necessary details such as date, time, and location.',
      status: 'completed',
      priority: 'medium'
    },
    {
      id: 5,
      title: 'Update API Documentation',
      description: 'Revise the API documentation to reflect ',
      status: 'new',
      priority: 'high'
    },
    {
      id: 6,
      title: 'Conduct Performance Reviews',
      description: 'Schedule and conduct performance reviews for all team members, providing constructive feedback and setting goals for the next quarter.',
      status: 'onGoing',
      priority: 'low'
    },
    {
      id: 7,
      title: 'Fix Bugs from Last Sprint',
      description: 'Identify and resolve the bugs reported during the last sprint, prioritizing those that impact user experience and functionality.',
      status: 'completed',
      priority: 'high'
    },
    {
      id: 8,
      title: 'Research Emerging Technologies',
      description: 'Investigate new technologies that could be beneficial for our upcoming project, focusing on their potential impact and implementation challenges.',
      status: 'new',
      priority: 'medium'
    },
    {
      id: 9,
      title: 'Organize Shared Drive Files',
      description: 'Review and reorganize the files in the shared drive to improve accessibility and ensure that all documents are properly categorized.',
      status: 'completed',
      priority: 'low'
    },
    {
      id: 10,
      title: 'Schedule Stakeholder Meeting',
      description: 'Coordinate with stakeholders to find a suitable time for the meeting, ensuring all key participants are available to discuss project updates.',
      status: 'onGoing',
      priority: 'high'
    },
    {
      id: 11,
      title: 'Backup Database',
      description: 'Perform a complete backup of the database to prevent data loss, ensuring that all recent changes are included in the backup.',
      status: 'new',
      priority: 'medium'
    },
    {
      id: 12,
      title: 'Draft Team Meeting Agenda',
      description: 'Create a detailed agenda for the next team meeting, outlining the topics to be discussed and allocating time for each item to ensure efficiency.',
      status: 'completed',
      priority: 'high'
    },
    {
      id: 13,
      title: 'Implement Feedback from Review',
      description: 'Review the feedback received from the last project review and implement necessary changes to improve the overall quality of the project deliverables.',
      status: 'onGoing',
      priority: 'medium'
    },
    {
      id: 14,
      title: 'Test New Features Before Release',
      description: 'Conduct thorough testing of all new features to ensure they function as intended and meet the quality standards before the official release.',
      status: 'new',
      priority: 'low'
    },
    {
      id: 15,
      title: 'Prepare Budget Proposal',
      description: 'Develop a detailed budget proposal for the next quarter, including projected expenses and justifications for each line item to secure funding.',
      status: 'completed',
      priority: 'medium'
    },
    {
      id: 16,
      title: 'Attend Project Management Workshop',
      description: 'Participate in a workshop focused on advanced project management techniques to enhance skills and improve team collaboration and efficiency.',
      status: 'onGoing',
      priority: 'high'
    },
    {
      id: 17,
      title: 'Collaborate with Design Team',
      description: 'Work closely with the design team to create a user-friendly interface for the new application, ensuring alignment with user needs and project goals.',
      status: 'new',
      priority: 'medium'

    }
  ];

  const [taskUnderEdit, setTaskUnderEdit] = useState({});
  const [tasksDetails, setTasksDetails] = useState({ tasks: tasksInit, nextTaskId: nextTaskIdInit });

  const newTasks = tasksDetails.tasks.filter((task) => task.status == 'new');
  const onGoingTasks = tasksDetails.tasks.filter((task) => task.status == 'onGoing');
  const completedTasks = tasksDetails.tasks.filter((task) => task.status == 'completed');

  
  return (

    <div className="App">

      <TaskContext.Provider value={[taskUnderEdit, setTaskUnderEdit]}>
        <TasksContext.Provider value={[tasksDetails,setTasksDetails]}>
          <NavBar></NavBar>
          <div className='container-fluid'>
            <div className="row g-3 justify-content-center">
              <TaskSection tasks={newTasks} status="new" title='New'></TaskSection>
              <TaskSection tasks={onGoingTasks} status="onGoing" title='On going'></TaskSection>
              <TaskSection tasks={completedTasks} status="completed" title='Completed'></TaskSection>
            </div>
          </div>
          <TaskEditModal></TaskEditModal>
        </TasksContext.Provider>
      </TaskContext.Provider>
    </div>
  );
}

export default App;