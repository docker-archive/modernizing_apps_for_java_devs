import React, { Component } from 'react';
import axios from 'axios';
import logo from './logo.svg';
import './App.css';
import './bootstrap-united.css';

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
    const state = this.state
    state[e.target.name] = e.target.value;
    this.setState(state);
  }

  onSubmit = (e) => {
    e.preventDefault();
    const { id, userName, password, firstName, lastName, dateOfBirth, emailAddress} = this.state;
    http://messageservice:8090/user
    axios.post('http://messageservice:8090/user', { id, userName, password, firstName, lastName, dateOfBirth, emailAddress })
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
        <div></div>
      <div>
      <div class="col-lg-6 col-lg-offset-3">
		    <div class="well">
			    <div class="container">
				    <div class="row">
					    <div class="col-lg-6">
                <form onSubmit={this.onSubmit} id="form2" class="bs-example form-horizontal" commandName="user">
                  <fieldset>
								    <legend>Signup</legend> 

                      <div class="form-group">
                        <label htmlFor="userName" class="col-lg-3 control-label">User Name </label>
                        <input type="text" name="userName" value={userName} onChange={this.onChange} class="col-lg-6" />

                      </div>

                      <div class="form-group">
                        <label htmlFor="password" class="col-lg-3 control-label">Password</label>
                        <input type="password" name="password" value={password} onChange={this.onChange} class="col-lg-6" />
                      </div>  
                    
                      <div class="form-group">
                        <label htmlFor="firstName" class="col-lg-3 control-label">First Name</label>
                          <input type="text" name="firstName" value={firstName} onChange={this.onChange} class="col-lg-6" />
                      </div>

                      <div class="form-group">
                        <label htmlFor="lastName" class="col-lg-3 control-label">Last Name</label>
                          <input type="text" name="lastName" value={lastName} onChange={this.onChange} class="col-lg-6"/>
                      </div>

                      <div class="form-group">
                        <label htmlFor="dateOfBirth" class="col-lg-3 control-label">Date of Birth</label>
                          <input type="date" name="dateOfBirth" value={dateOfBirth} onChange={this.onChange} class="col-lg-6"/>
                      </div>

                      <div class="form-group">
                        <label htmlFor="emailAddress" class="col-lg-3 control-label">Email</label>
                          <input type="text" name="emailAddress" value={emailAddress} onChange={this.onChange} class="col-lg-6"/>
                      </div>
            
                      <input type="hidden" name="id" value={id} onChange={this.onChange} />
                      <div class="btn-toolbar">
                        <button class="btn btn-default">Cancel</button>
                      
                        <button type="submit" className="btn btn-primary">Submit</button>
                      </div>
                  </fieldset>
                </form>
              </div>  
            </div>  
          </div>  
        </div>    
      </div>  
      </div>
      </div>
    );
  }
}

export default App;
