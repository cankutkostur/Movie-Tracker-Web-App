import React from 'react';
import {Link} from 'react-router-dom';

class MainPage extends React.Component{
    render(){
        return (
            <div align="center">
                <Link to="/directors">Directors</Link>
                <br/>
                <Link to="/directors/add">Add Director</Link>
                <br/>
                <Link to="/movies">Movies</Link>
                <br/>
                <Link to="/movies/add">Add Movie</Link>
                <br/>
                <Link to="/users">Users</Link>
                <br/>
                <Link to="/users/add">Add User</Link>
                <br/>
            </div>
        )
    }
}

export default MainPage;