import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import LoginPage from "./login"
import DirectorAdd from "./directorAdd"
import DirectorList from "./directorList"
import MovieAdd from './movieAdd';
import MovieList from './moviesList'
import MainPage from './MainPage';
import AppRouter from './AppRouter';

ReactDOM.render(<AppRouter />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();