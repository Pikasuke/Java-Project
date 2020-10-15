import React, { Component } from 'react'

class FileInput extends Component {
    constructor(props) {
      super(props);
      this.handleSubmit = this.handleSubmit.bind(this);
      this.fileInput = React.createRef();
    }

    handleSubmit(event) {
      event.preventDefault();
      console.log(this.fileInput.current.files[0])
      const form = new FormData()
      form.append("file", this.fileInput.current.files[0])
      fetch("url", {
        method: "POST",
        body: form
      })
    }
  
    render() {
      return (
        <form onSubmit={this.handleSubmit}>
          <label>
            Envoyer un fichier :
            <input type="file" ref={this.fileInput} />        </label>
          <br />
          <button type="submit">Envoyer</button>
        </form>
      );
    }
  }
  
  export default FileInput