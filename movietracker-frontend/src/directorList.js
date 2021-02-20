import React from 'react'

class DirectorList extends React.Component{
    constructor(props){
        super(props)
        this.state = {directors : [], status:"Loading", update : false, name: "", surname: "", birthPlace: "", birthDate: "", messages : [], directorId : 0}
        this.handleDelete = this.handleDelete.bind(this)
        this.handleUpdate = this.handleUpdate.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.handleClick = this.handleClick.bind(this)
    }

    handleDelete(event){
        this.setState({status:"Loading"})
        fetch("http://localhost:8080/directors/" + event.target.id, {
                method : "DELETE",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
        }).then(response => response.json())
        .then(responseJson => {
            this.setState({status: "Loading"}, this.getfunc);
        })
    }

    handleChange(event){
        this.setState({[event.target.name] : event.target.value})
    }

    handleClick(event){
        let results = []
        if(this.state.name === "" ){
            results.push("Name can not be empty!")
        }
        if(this.state.surname === "" ){
            results.push("Surname can not be empty!")
        }
        if(this.state.birthPlace === "" ){
            results.push("Birth place can not be empty!")
        }
        if(this.state.birthPlace === "" ){
            results.push("Birth date can not be empty!")
        }
        if(results.length === 0){
            fetch("http://localhost:8080/directors/" + this.state.directorId, {
                method : "PUT",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                },
                body: JSON.stringify({
                    name : this.state.name,
                    surname : this.state.surname,
                    birthPlace : this.state.birthPlace,
                    birthDate : this.state.birthDate
                })
            }).then(response => 
                response.json()
                ).then(responseJson => 
                    {results.push(responseJson.accessToken)
                    this.setState({messages : results},this.getfunc)}
                    ).then(this.setState({update : false}))
        }
        console.log(results)
        this.setState({messages : results})
    }

    handleUpdate(event){
        fetch("http://localhost:8080/directors/" + event.target.id, {
                method : "GET",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
            }).then(response => 
                response.json()
                ).then(data => 
                    {this.setState({directorId : data.id, name : data.name, surname : data.surname, birthDate : data.birthDate, birthPlace : data.birthPlace, status : "Success"})}
                ).catch(error =>{
                    console.error(error)
                    this.setState({status : "Fail"})
                }).then(this.setState({update : true}))
    }
    

    componentDidMount(){
        this.getfunc();
    }

    getfunc(){
        fetch("http://localhost:8080/directors", {
                method : "GET",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                }
            }).then(response => 
                response.json()
                ).then(data => 
                    {this.setState({directors : data, status : "Success"})}
                ).catch(error =>{
                    console.error(error)
                    this.setState({status : "Fail"})
                })
    }

    render(){
        if(this.state.update){
            console.log(this.state);
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
                                        Surname: 
                                    </th>
                                    <td>
                                        <input name="surname" type="text" value={this.state.surname} onChange={this.handleChange}/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        Birth Place: 
                                    </th>
                                    <td>
                                        <input name="birthPlace" type="text" value={this.state.birthPlace} onChange={this.handleChange}/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        Birth Date: 
                                    </th>
                                    <td>
                                        <input name="birthDate" type="date" value={this.state.birthDate} onChange={this.handleChange}/>
                                    </td>
                                </tr>
                                <tr>
                                    <td colSpan="2">
                                        <button type="button" onClick = {this.handleClick} >Update</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    {this.state.messages.map(item => <p style={{color : 'red'}}>{item}</p>)}
                </div>
            )
        }
        if(this.state.status !== "Success"){
            return <div align="center">{this.state.status}</div>
        }
        else{
            const directorsView = this.state.directors.map(director =>(
                <div align="center">
                    <table border="1">
                    <tbody>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Birth Place</th>
                            <th>Birth Date</th>
                            <th>Action</th>
                        </tr>
                        <tr>
                            <td>{director.name}</td>
                            <td>{director.surname}</td>
                            <td>{director.birthPlace}</td>
                            <td>{director.birthDate}</td>
                            <td><input type="button" value="Update" id={director.id} onClick = {this.handleUpdate}/>
                            <input type="button" value="Delete" id={director.id}  onClick = {this.handleDelete}/></td>
                        </tr>
                    </tbody>
                </table><br/>
                </div>
            ))
            return directorsView;
        }
    }
}

export default DirectorList;