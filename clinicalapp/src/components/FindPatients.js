import React from 'react';
import {Link} from 'react-router-dom';

class FindPatients extends React.Component{

    handleSubmit(event){
        this.props.history.push('displayPatients/'+this.firstName+'/'+this.lastName)
    }

    render(){
        return (<div>

            <h2>Find Patients:</h2>
            <form>
                First Name:<input type="text" name="firstName" onChange={(event)=>{this.firstName=event.target.value}}/>
                Last Name:<input type="text" name="lastName" onChange={(event)=>{this.lastName=event.target.value}}/>
                <button type="button" onClick={this.handleSubmit.bind(this)}>Search</button>
            </form>
        </div>)
    }
}

export default FindPatients;