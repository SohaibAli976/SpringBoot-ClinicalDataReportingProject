import React from 'react';
import axios from 'axios';
import {Link} from 'react-router-dom';

class DisplayPatients extends React.Component{
    state ={
        patientData:[]
    }

    componentWillMount(){
         axios.get('http://localhost:8080/clinicalservices/api/patients?firstName='+this.props.match.params.firstName+
        '&lastName='+this.props.match.params.lastName).then(res=>{
            const patientData = res.data;
            this.setState({patientData})
        })
    }

    render(){
        return (<div>
            <h2>Patients:</h2>
            <table>
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                    </tr>
                </thead>
                <tbody>
                    {this.state.patientData.map(patient=><RowCreator item={patient}/>)}
                </tbody>
            </table>
        </div>)
    }
}

class RowCreator extends React.Component{
    render(){
        var patient = this.props.item;
        return <tr>
            <td>{patient.firstName}</td>
            <td>{patient.lastName}</td>
            <td>{patient.age}</td>
            <td><Link to={'/patientDetails/'+patient.id}>Add Data</Link></td>
            <td><Link to={'/analyze/'+patient.id}>Analyze</Link></td>
        </tr>
    }
}



export default DisplayPatients;