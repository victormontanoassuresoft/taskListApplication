import './App.css';
import { Router } from '@reach/router';
import Home from './home/Home';
import Details from './details/Details';

function App() {
  return (
    <div className="App">
      <Router>
        <Home path="/"/>
        <Details path="/task/:taskId"/>
        <Details path="/task/create" /> 
      </Router>
    </div>
  );
}

export default App;
