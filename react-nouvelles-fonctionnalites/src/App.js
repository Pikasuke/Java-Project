import React from 'react'
import { BrowserRouter as Router, Route, NavLink } from "react-router-dom"
import Home from "./pages/Home"
import ChangeBackground from './pages/UseState/ChangeBackground'
import './App.css'
import Layout from './hoc/Layout'
import withClass from './hoc/WithClass'
import Counter from './pages/UseCallback/Counter';
import CounterEffect from './pages/UseEffect/CounterEffect';

const App = () => {
  return (
    <Router>
      <Layout>
        {/* Ici, les composants Route sont des enfants de Layout */}
        <Route path="/" exact component={withClass(Home, "colorBlue")} />
        <Route path="/change-background" component={ChangeBackground} />
        <Route path="/compteur" component={Counter} />
        <Route path="/compteur-effect" component={CounterEffect} />
      </Layout>
    </Router>
  );
}

export default App;
