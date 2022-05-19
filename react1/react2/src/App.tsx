import React from 'react';
import './App.css';

import {BrowserRouter, Routes, Route } from 'react-router-dom'
import {LoginPage} from './Views/LoginPage/LoginPage';
import { FeedPage } from './Views/FeedPage/FeedPage';

function App() {
  return (
    <BrowserRouter>
    <Routes>
      <Route path="/login" element={<LoginPage />}/>
      <Route path="/feed"  element={<FeedPage/>}/>
      </Routes>
          </BrowserRouter>
  );
}

export default App;
