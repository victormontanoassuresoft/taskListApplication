import './App.css';
import { Router } from '@reach/router';
import Home from './home/Home';
import Details from './details/Details';
import Timezone from './timezone/Timezone';
import { createStore } from 'redux';
import { Provider } from 'react-redux';
import { Task } from './common/types';
import { getTaskList } from './home/home.api';

const initialState = {
  tasks: []
}

function reducer(state = initialState, action) {
  switch(action.type) {
    case 'GETLIST':
      return {
        tasks: state.tasks = getTaskList()
      }
    default:
      return state;
  }
  return state
}
const store = createStore(reducer);

function App() {
  return (
    <div className="App">
      <Router>
        <Provider store={store}>
          <Home path="/"/>
        </Provider>
        <Details path="/task/:taskId"/>
        <Details path="/task/create" /> 
        <Timezone path="/timezone/:coordinate" />
        <Timezone path="/timezone" />
      </Router>
    </div>
  );
}

export default App;
