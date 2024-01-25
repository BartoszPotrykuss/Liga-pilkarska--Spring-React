import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import AddTeam from './components/AddTeam';
import Header from './components/Header';
import NavBar from './components/NavBar';
import TeamList from './components/TeamList';
import NoPage from './components/NoPage';

function App() {
  return (
    <BrowserRouter>
      <Header title="Liga Piłkarska"/>
      <NavBar />
      <Routes>
        <Route path='/' index element={<TeamList />}></Route> 
        <Route path='/teamList' element={<TeamList />}></Route> 
        <Route path='/addTeam' element={<AddTeam />}></Route>
        <Route path='*' element={<NoPage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
