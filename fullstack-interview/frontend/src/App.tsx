import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import AddView from './views/AddView';
import ListView from './views/ListView';
import EditView from './views/EditView';

function App() {
  return <BrowserRouter>
            <Switch>
              <Route exact path="/" component={ListView} />
              <Route path="/add" component={AddView}/>
              <Route path="/edit/:id" component={EditView}/>
            </Switch>
        </BrowserRouter>;
}

export default App;
