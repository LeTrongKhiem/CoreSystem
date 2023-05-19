import './App.css';
import "./assets/boxicons-2.0.7/css/boxicons.min.css";
import "./assets/css/grid.css";
import "./assets/css/index.css";
import 'react-toastify/dist/ReactToastify.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {AddBookExchange, DetailBook, Home, Login, Register, VerificationPage} from "./pages";
import {ToastContainer} from "react-toastify";
import {Provider} from "react-redux";
import {store} from "./redux/store";

function App() {
    return (<Provider store={store}>
        <BrowserRouter>

            <div className="layout">
                <div className="layout__content">
                    <div className="layout__content-main">
                        <Routes>
                            <Route path="/" element={<Home/>}/>
                            <Route path="/post-book" element={<AddBookExchange/>}/>
                            <Route path="/detail-book/:id" element={<DetailBook/>}/>
                            <Route path="/login" element={<Login/>}/>
                            <Route path="/register" element={<Register/>}/>
                            <Route path="/verify" element={<VerificationPage/>}/>
                        </Routes>
                    </div>
                </div>
            </div>
            <ToastContainer/>

        </BrowserRouter>
    </Provider>);
}

export default App;
