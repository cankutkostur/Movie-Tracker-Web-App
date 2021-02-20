import React from 'react'

class DirectorAdd extends React.Component{
    constructor(props){
        super(props)
        this.state = { name: "", surname: "", birthPlace: "", birthDate: "", messages : []}
        this.handleChange = this.handleChange.bind(this)
        this.handleClick = this.handleClick.bind(this)
    }
    
    handleChange(event){
        this.setState({[event.target.name] : event.target.value})
    }

    handleClick(){
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
            fetch("http://localhost:8080/directors", {
                method : "POST",
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
                    this.setState({messages : results})}
                    )
                    results.push("Added!")
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

export default DirectorAdd;