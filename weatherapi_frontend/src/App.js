import React, {Component, useState, useEffect} from 'react';
import logo from './logo.svg';
import './App.css';

function App () {
  const [message, setMessage] = useState("");
    const [city, setCity] = useState("");


    const onSubmit = (e) => {
      e.preventDefault();
      setCity(e.target.value)

  }

  useEffect(() => {
    fetch('/city/{city}')
        .then(response => response.text())
        .then(message => {
          setMessage(message);
        });
  },[])
  return (
      <div className="App">
          <form>
        <input type="text" placeholder="Enter City" value = {city}/>
        <button onClick={onSubmit} type="submit">Find</button>

          </form>
        <p className="App-intro">
           Hello
        </p>
      </div>
  )
}

export default App;
