import React from 'react'
import { thisExpression } from '@babel/types';

class MovieList extends React.Component{
    constructor(props){
        super(props)
        this.state = {movieId : undefined, name : "", director : undefined, releaseDate: undefined, imdbRating : 0, duration : 0, genre : "",  messages : [], directors : [], listName : "", movies :[]}
        
        this.handleChange = this.handleChange.bind(this)
        this.handleListChange = this.handleListChange.bind(this)
        this.handleClick = this.handleClick.bind(this)
        this.handleRemoveFav = this.handleRemoveFav.bind(this)
        this.handleAddFav = this.handleAddFav.bind(this)
        this.handleRemoveWatched = this.handleRemoveWatched.bind(this)
        this.handleAddWatched = this.handleAddWatched.bind(this)
    }

    getfuncfav(){
        fetch("http://localhost:8080/favourites", {
                method : "GET",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
            }).then(response => 
                response.json()
                ).then(data => 
                    {if(Array.isArray(data)){
                        this.setState({movies : data, status : "Success"})
                    }
                    else{
                        this.setState({movies : [], status : "Success", name : "", director : "", releaseDate: "", imdbRating : 0, duration : 0, genre : ""})
                    }}
                ).catch(error =>{
                    console.error(error)
                    this.setState({status : "Fail"})
                })
    }

    getfuncwatched(){
        fetch("http://localhost:8080/watched", {
                method : "GET",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
            }).then(response => 
                response.json()
                ).then(data => 
                    {if(Array.isArray(data)){
                        this.setState({movies : data, status : "Success"})
                    }
                    else{
                        this.setState({movies : [], status : "Success", name : "", director : "", releaseDate: "", imdbRating : 0, duration : 0, genre : ""})
                    }}
                ).catch(error =>{
                    console.error(error)
                    this.setState({status : "Fail"})
                })
    }

    getfuncall(){
        fetch("http://localhost:8080/movies", {
                method : "GET",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
            }).then(response => 
                response.json()
                ).then(data => 
                    {
                        if(Array.isArray(data)){
                            this.setState({movies : data, status : "Success"})
                        }
                        else{
                            this.setState({movies : [], status : "Success", name : "", director : undefined, releaseDate: undefined, imdbRating : 0, duration : 0, genre : ""})
                        }
                    }
                ).catch(error =>{
                    console.error(error)
                    this.setState({status : "Fail"})
                })
    }


    handleAddWatched(event){
        this.setState({status:"Loading"})
        fetch("http://localhost:8080/watched/" + event.target.id, {
                method : "POST",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
        }).then(response => response.json())
        .then(responseJson => {
            this.setState({status: "Loading"}, this.getfuncall);
        })
    }

    handleRemoveWatched(event){
        this.setState({status:"Loading"})
        fetch("http://localhost:8080/watched/" + event.target.id, {
                method : "DELETE",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
        }).then(response => response.json())
        .then(responseJson => {
            this.setState({status: "Loading"}, this.getfuncwatched);
        })
    }

    handleAddFav(event){
        console.log(event.target)
        this.setState({status:"Loading"})
        fetch("http://localhost:8080/favourites/" + event.target.id, {
                method : "POST",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
        }).then(response => response.json())
        .then(responseJson => {
            this.setState({status: "Loading"}, this.getfuncall);
        })
    }

    handleRemoveFav(event){
        console.log(event.target)
        this.setState({status:"Loading"})
        fetch("http://localhost:8080/favourites/" + event.target.id, {
                method : "DELETE",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
        }).then(response => response.json())
        .then(responseJson => {
            this.setState({status: "Loading"}, this.getfuncwatched);
        })
    }

    handleChange(event){
        if(event.target.type === "checkbox"){
            this.setState({[event.target.name] : event.target.checked})
        }
        this.setState({[event.target.name] : event.target.value}, () => console.log(this.state))
    }

    handleListChange(event){
        if(event.target.type === "checkbox"){
            this.setState({[event.target.name] : event.target.checked})
        }
        this.setState({[event.target.name] : event.target.value}, () => console.log(this.state))
        if(this.state.listName === "watched"){
            this.getfuncwatched()
        }
        else if(this.state.listName === "favourites"){
            this.getfuncfav()
        }
        else{
            this.getfuncall()
        }
        console.log(this.state.movies)
    }

    handleClick(){
        let results = []
        if(this.state.name === "" ){
            results.push("Name can not be empty!")
        }
        if(this.state.directorId == 0){
            results.push("Director can not be empty!")
        }
       
        if(results.length === 0){
            fetch("http://localhost:8080/movies", {
                method : "PUT",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                },
                body: JSON.stringify({
                    name : this.state.name,
                    directorId : this.state.directorId,
                    releaseDate : this.state.releaseDate,
                    imdbRating : this.state.imdbRating,
                    duration : this.state.duration,
                    genre : this.state.genre
                })
            }).then(response => 
                response.json()
                ).then(responseJson => 
                    {results.push(responseJson.accessToken)
                    this.setState({messages : results})}
                    )
        }
        console.log(results)
        this.setState({messages : results})
    }

    componentDidMount(){
        this.getfuncall()
    }

    render(){
        if(this.state.listName === "watched"){
            const moviesView = this.state.movies.map(movie =>(
                <div align="center">
                    <p>Movies: 
                    <select name="listName" onChange={this.handleListChange}>
                        <option value="">All</option>
                        <option value="favourites">Favourites</option>
                        <option value="watched">Watched</option>
                    </select></p>
                    <table border="1">
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Director</th>
                            <th>Release Date</th>
                            <th>IMDB Rating</th>
                            <th>Duration</th>
                            <th>Genre</th>
                            <th>Action</th>
                        </tr>
                        <tr>
                            <td>{movie.name}</td>
                            <td>{movie.director.name}</td>
                            <td>{movie.releaseDate}</td>
                            <td>{movie.imdbRating}</td>
                            <td>{movie.duration}</td>
                            <td>{movie.genre}</td>
                            <td><input type="button" value="Remove" id={movie.id} onClick = {this.handleRemoveWatched}/></td>
                        </tr>
                    </tbody>
                </table><br/>
                </div>
            ))
            return moviesView;
        }
        else if(this.state.listName === "favourites"){
            const moviesView = this.state.movies.map(movie =>(
                <div align="center">
                    <p>Movies: 
                    <select name="listName" onChange={this.handleListChange}>
                        <option value="">All</option>
                        <option value="favourites">Favourites</option>
                        <option value="watched">Watched</option>
                    </select></p>
                    <table border="1">
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Director</th>
                            <th>Release Date</th>
                            <th>IMDB Rating</th>
                            <th>Duration</th>
                            <th>Genre</th>
                            <th>Action</th>
                        </tr>
                        <tr>
                            <td>{movie.name}</td>
                            <td>{movie.director.name}</td>
                            <td>{movie.releaseDate}</td>
                            <td>{movie.imdbRating}</td>
                            <td>{movie.duration}</td>
                            <td>{movie.genre}</td>
                            <td><input type="button" value="Remove" id={movie.id} onClick = {this.handleRemoveFav}/></td>
                        </tr>
                    </tbody>
                </table><br/>
                </div>
            ))
            return moviesView;
        }
        else{  
            const moviesView = this.state.movies.map(movie =>(
                <div align="center">
                    <p>Movies: 
                    <select name="listName" onChange={this.handleListChange}>
                        <option value="">All</option>
                        <option value="favourites">Favourites</option>
                        <option value="watched">Watched</option>
                    </select></p>
                    <table border="1">
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Director</th>
                            <th>Release Date</th>
                            <th>IMDB Rating</th>
                            <th>Duration</th>
                            <th>Genre</th>
                            <th>Action</th>
                        </tr>
                        <tr>
                            <td>{movie.name}</td>
                            <td>{movie.director.name}</td>
                            <td>{movie.releaseDate}</td>
                            <td>{movie.imdbRating}</td>
                            <td>{movie.duration}</td>
                            <td>{movie.genre}</td>
                            <td><input type="button" value="Fav" id={movie.id} onClick = {this.handleAddFav}/>
                            <input type="button" value="Watched" id={movie.id}  onClick = {this.handleAddWatched}/></td>
                        </tr>
                    </tbody>
                </table><br/>
                </div>
            ))
            return moviesView;
        }
    }
}
export default MovieList;