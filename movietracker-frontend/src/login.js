import React from 'react';
import { identifier } from '@babel/types';

class LoginPage extends React.Component{
    constructor(props){
        super(props)
        this.state = {username : "", password : "", messages : []}
        this.handleChange = this.handleChange.bind(this)
        this.handleClick = this.handleClick.bind(this)
    }

    handleChange(event){
        this.setState({[event.target.name] : event.target.value})
    }

    handleClick(){
        let results = []
        if(this.state.username === ""){
            results.push("Username can not be empty!")
        }
        if(this.state.password === ""){
            results.push("Password can not be empty!")
        }
        if(results.length === 0){
            fetch("http://localhost:8080/login", {
                method : "POST",
                headers : {
                    "Content-type" : "application/json; charset=UTF-8"
                },
                body: JSON.stringify({
                    username : this.state.username,
                    password : this.state.password
                })
            }).then(response => 
                response.json()
                ).then(responseJson => 
                    {
                        console.log(responseJson)
                        if(responseJson.error === "Unauthorized"){
                            results.push("wrong username or password")
                            this.setState({messages : results})
                        }
                        else{
                            localStorage.setItem("token", ("Bearer " + responseJson.accessToken))
                            this.props.history.push("/main")
                        }
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
                                    Password: 
                                </th>
                                <td>
                                    <input name="password" type="password" value={this.state.password} onChange={this.handleChange}/>
                                </td>
                            </tr>
                            <tr>
                                <td colSpan="2">
                                    <button type="button" onClick = {this.handleClick}>Login</button>
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

export default LoginPage;