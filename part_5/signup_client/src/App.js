import React, { Component } from 'react';
import axios from 'axios';
import logo from './logo.svg';
import './App.css';

class App extends Component {

  constructor( props ) {
    var randomInt = require('random-int');
    var val = randomInt(1,10000);
    super();
    this.state = {
      id: val,
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
    const { id, userName, password, firstName, lastName, dateOfBirth, emailAddress} = this.state;

    axios.post('http://localhost:8090/user', { id, userName, password, firstName, lastName, dateOfBirth, emailAddress })
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
    const { id, userName, password, firstName, lastName, dateOfBirth, emailAddress } = this.state;
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
            <input type="password" name="password" value={password} onChange={this.onChange} />
            <br/>
            <label htmlFor="firstName">First Name</label>
            <input type="text" name="firstName" value={firstName} onChange={this.onChange} />
            <br/>
            <label htmlFor="lastName">Last Name</label>
            <input type="text" name="lastName" value={lastName} onChange={this.onChange} />
            <br/>
            <label htmlFor="dateOfBirth">Date of Birth</label>
            <input type="date" name="dateOfBirth" value={dateOfBirth} onChange={this.onChange} />
            <br/>
            <label htmlFor="emailAddress">Email</label>
            <input type="text" name="emailAddress" value={emailAddress} onChange={this.onChange} />
            <br/>
            <input type="hidden" name="id" value={id} onChange={this.onChange} />
            <button type="submit" className="mb-4 btn btn-primary">Submit</button>
          </form>
        </div>  
      </div>
    );
  }
}

export default App;
