import './App.css';
import { Router } from '@reach/router';
import Home from './home/Home';
import Details from './details/Details';
import Timezone from './timezone/Timezone';

function App() {
  return (
    <div className="App">
      <Router>
        <Home path="/"/>
        <Details path="/task/:taskId"/>
        <Details path="/task/create" /> 
        <Timezone path="/timezone/:lat/:lng" />
        <Timezone path="/timezone" />
      </Router>
    </div>
  );
}

export default App;
