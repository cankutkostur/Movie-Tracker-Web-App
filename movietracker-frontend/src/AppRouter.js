import React from "react";
import { BrowserRouter, Route, Redirect } from "react-router-dom";
import LoginPage from "./login";
import DirectorList from "./directorList";
import DirectorAdd from "./directorAdd";
import MovieAdd from "./movieAdd";
import MovieList from "./moviesList";
import MainPage from "./MainPage";
import UserAdd from "./userAdd";

const AppRouter = () => (
    <BrowserRouter>
        <Route exact path="/users/add" component={UserAdd} />
        <Route exact path="/main" component={MainPage} />
        <Route exact path="/directors" component={DirectorList} />
        <Route exact path="/directors/add" component={DirectorAdd} />
        <Route exact path="/movies" component={MovieList} />
        <Route exact path="/movies/add" component={MovieAdd} />
        <Route exact path="/" component={LoginPage} />
    </BrowserRouter>
);

export default AppRouter;