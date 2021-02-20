import React from 'react'

class UserAdd extends React.Component{
    constructor(props){
        super(props)
        this.state = { username: "", name: "", surname: "", email: "", password: "", isAdmin : "", messages : []}
        this.handleChange = this.handleChange.bind(this)
        this.handleClick = this.handleClick.bind(this)
    }
    
    handleChange(event){
        if(event.target.type === "checkbox"){
            this.setState({[event.target.name] : event.target.checked})
        }
        this.setState({[event.target.name] : event.target.value})
    }

    handleClick(){
        let results = []
        if(this.state.username === "" ){
            results.push("Username can not be empty!")
        }
        if(this.state.name === "" ){
            results.push("Name can not be empty!")
        }
        if(this.state.surname === "" ){
            results.push("Surname can not be empty!")
        }
        if(this.state.email === "" ){
            results.push("Email can not be empty!")
        }
        if(this.state.password === "" ){
            results.push("Password can not be empty!")
        }

        if(results.length === 0){
            fetch("http://localhost:8080/users", {
                method : "POST",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8",
                    "Authorization" : localStorage.getItem("token")
                },
                body: JSON.stringify({
                    username : this.state.username,
                    name : this.state.name,
                    surname : this.state.surname,
                    email : this.state.email,
                    password : this.state.password,
                    admin : this.state.isAdmin === "true"
                })
            }).then(response => 
                response.json()
                ).then(responseJson => 
                    {
                    this.setState({messages : results})}
                    )
        }
        console.log(results)
        this.setState({messages : results})
    }

    render(){
        return(
            <div align="center">
                <form>
                    <table>
                        <tbody>
                            <tr>
                                <th>
                                    Username: 
                                </th>
                                <td>
                                    <input name="username" type="text" value={this.state.username} onChange={this.handleChange}/>
                                </td>
                            </tr>
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
                                    Email: 
                                </th>
                                <td>
                                    <input name="email" type="text" value={this.state.email} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Password: 
                                </th>
                                <td>
                                    <input name="password" type="password" value={this.state.password} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    Admin: 
                                </th>
                                <td>
                                    <input type="radio" name="isAdmin" value="true" onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    User: 
                                </th>
                                <td>
                                    <input type="radio" name="isAdmin" value="false" onChange={this.handleChange}/>
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

export default UserAdd;