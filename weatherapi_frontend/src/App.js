import React, {useState} from 'react';
import './App.css';

function App () {
  const [message, setMessage] = useState("");
    const [city, setCity] = useState("");


    const handleChange = (e) => {
      setCity(e.target.value);
  }

  const handleSubmit = async (e) => {
        e.preventDefault();
      await fetch(`/city/${city}`)
          .then(response => response.text())
          .then(message => {
              setMessage(message);
              console.log(message);
          });
  }

  return (
      <div className="App">
          <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Enter City" value = {city} onChange={handleChange}/>
        <button type="submit">Find</button>

          </form>
          <h2 className="heading">Display Section</h2>
        <p className="section">
            {message}
        </p>
      </div>
  )
}

export default App;
