import React, { Component } from 'react';
// import { Form, Text } from 'react-form';
import axios from 'axios';
import './App.css';

class App extends Component {

  constructor( props ) {
    super();
    this.state = {
      userName: '',
      password: '',
      firstName: '',
      lastName: '',
      dateOfBirth: '',
      emailAddress: ''
    };
  }

  onChange = (e) => {
    // Because we named the inputs to match their corresponding values in state, it's
    // super easy to update the state
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();
    // get our form data out of state
    const { userName, password, firstName, lastName, dateOfBirth, emailAddress} = this.state;

    axios.post('http://localhost:8090/user', { userName, password, firstName, lastName, dateOfBirth, emailAddress })
      // .then((result) => {
        //access the results here....
        .then(function (response) {
          console.log(response);
        })
        .catch(function (error) {
          console.log(error);  
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
      });
  }

  render() {
    const { userName, password, firstName, lastName, dateOfBirth, emailAddress } = this.state;
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title">Welcome to Signup</h1>
        </header>
        <p className="App-intro">
          Signup
        </p>
        <div>
          <form onSubmit={this.onSubmit} id="form2">
            <label htmlFor="userName">User Name</label>
            <input type="text" name="userName" value={userName} onChange={this.onChange} />
            <br/>
            <label htmlFor="password">Password</label>
            <input type="text" name="password" value={password} onChange={this.onChange} />
            <br/>
            <label htmlFor="firstName">First Name</label>
            <input type="text" name="firstName" value={firstName} onChange={this.onChange} />
            <br/>
            <label htmlFor="lastName">Last Name</label>
            <input type="text" name="lastName" value={lastName} onChange={this.onChange} />
            <br/>
            <label htmlFor="dateOfBirt">Date of Birth</label>
            <input type="text" name="dateOfBirth" value={dateOfBirth} onChange={this.onChange} />
            <br/>
            <label htmlFor="emailAddress">Email</label>
            <input type="text" name="emailAddress" value={emailAddress} onChange={this.onChange} />
            <br/>
            <button type="submit" className="mb-4 btn btn-primary">Submit</button>
          </form>
        </div>  
      </div>
    );
  }
}

export default App;
