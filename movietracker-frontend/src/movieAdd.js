import React from 'react'

class MovieAdd extends React.Component{
    constructor(props){
        super(props)
        this.state = {movieId : undefined, name: "", directorId: 0, releaseDate: "", imdbRating: undefined, duration : "", genre : "", messages : [], directors : []}
        this.handleChange = this.handleChange.bind(this)
        this.handleClick = this.handleClick.bind(this)
    }
    
    handleChange(event){
        if(event.target.type === "checkbox"){
            this.setState({[event.target.name] : event.target.checked})
        }
        this.setState({[event.target.name] : event.target.value}, () => console.log(this.state))
    }

    handleClick(){
        let results = []
        console.log(this.state.directors)
        if(this.state.name === "" ){
            results.push("Name can not be empty!")
        }
        if(this.state.directorId == 0){
            results.push("Director can not be empty!")
        }
       
        if(results.length === 0){
            fetch("http://localhost:8080/movies", {
                method : "POST",
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
                    results.push("Added!")
        }
        console.log(results)
        this.setState({messages : results})
    }

    componentDidMount(){
        fetch("http://localhost:8080/directors", {
                method : "GET",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
            }).then(response => 
                response.json()
                ).then(data => 
                    {this.setState({directors : data})
                })
    }

    render(){
        return(
            <div align="center">
                <form>
                    <table>
                        <tbody>
                            <tr>
                                <th>
                                    Name: 
                                </th>
                                <td>
                                    <input name="name" type="text" value={this.state.name} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Release Date: 
                                </th>
                                <td>
                                    <input name="releaseDate" type="date" value={this.state.releaseDate} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    imdbRating: 
                                </th>
                                <td>
                                    <input name="imdbRating" type="number" placeholder="1.0" step="0.1" min="0" max="10" value={this.state.imbdRating} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Duration(mins): 
                                </th>
                                <td>
                                    <input name="duration" type="number" placeholder="1.0" step="0.1" min="0" value={this.state.duration} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Genre: 
                                </th>
                                <td>
                                    <input name="genre" type="text" value={this.state.genre} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Director: 
                                </th>
                                <td>
                                    <select name="directorId" onChange={this.handleChange} >
                                        <option value="0">Select Director</option>
                                        {this.state.directors.map(item => <option value={item.id}>{item.name}</option>)}
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <button type="button" onClick = {this.handleClick}>Add</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                {this.state.messages.map(item => <p style={{color : 'red'}}>{item}</p>)}
            </div>
        )
    }
}

export default MovieAdd;