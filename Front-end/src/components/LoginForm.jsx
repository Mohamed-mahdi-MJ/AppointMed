import React from "react";
import { IoIosMail } from "react-icons/io";
import { IoEye } from "react-icons/io5";
import { IoIosClose } from "react-icons/io";
import { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
    const [pass, setPass] = useState(false);

    const togglePass = () => {
        setPass((prevItemm) => !prevItemm);
    };

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const [role, setRole] = useState("patient");
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/api/auth/generateToken", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ username: email, password }),
            });

            console.log(response);

            if (!response.ok) throw new Error("Failed to login");

            const token = await response.text();
            console.log(token);
            localStorage.setItem("token", token);

            const profileResponse = await fetch(`http://localhost:8080/api/${role}/profile`, {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            console.log(profileResponse);

            if (!profileResponse.ok) throw new Error("Failed to fetch profile");

            const profile = await profileResponse.json();
            localStorage.setItem("id", profile.id);
            localStorage.setItem("name", profile.name);
            localStorage.setItem("email", profile.email);
            localStorage.setItem("role", profile.role);
            localStorage.setItem("department", profile.department);

            // Redirect to the home page
            profile.role==="PATIENT"?navigate("/home"):navigate("/request");
        } catch (error) {
            console.error("Login error:", error);
        }
    };

    return (
        <form onSubmit={handleLogin} className="flex flex-col items-center  2xl:gap-8   ssm:gap-5 sm:gap-6">
            <h1 className="lg:text-4xl ssm:text-[2.6rem] font-medium">Welcome Back</h1>
            <section className="flex flex-col items-center gap-4 sm:mt-0 ssm:mt-[2rem]">
                <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                    <input type="email" name="email" placeholder="Email" onChange={(e) => setEmail(e.target.value)} className="text-dark bg-inherit outline-none border-none text-lg" />
                    <IoIosMail className="text-xl" />
                </div>
                <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                    <input type={`${pass ? "text" : "password"}`} name="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} className="text-dark bg-inherit outline-none border-none text-lg" />
                    {pass ? <IoIosClose className="text-xl cursor-pointer" onClick={() => togglePass()} /> : <IoEye className="text-xl cursor-pointer" onClick={() => togglePass()} />}
                </div>
            </section>
            <section className="flex flex-row items-center gap-8 sm:py-0 ssm:py-[1rem]">
                <div className="flex items-center gap-2 text-xl font-medium">
                    <input type="radio" id="patient" name="sign" value="patient"
                        checked={role === "patient"}
                        onChange={() => setRole("patient")} />
                    <label htmlFor="patient">Patient</label>
                </div>
                <div className="flex items-center gap-2 text-xl font-medium">
                    <input type="radio" id="doctor" name="sign" value="doctor"
                        checked={role === "doctor"}
                        onChange={() => setRole("doctor")} />
                    <label htmlFor="doctor">Doctor</label>
                </div>
            </section>

            <section className="flex flex-col items-center gap-4">
                <button type="submit" aria-label="SignIn" className="bg-dark text-lg font-medium sm:px-12 sm:py-2 ssm:px-24 ssm:py-[0.6rem] text-light rounded-full">
                    Sign In
                </button>
                <div className="flex gap-4">
                    <h2 className="text-lightblack">Dont have an account ?</h2>
                    <Link to={"/signup"} className="hover:underline cursor-pointer underline-offset-[3px] font-semibold">
                        Sign Up
                    </Link>
                </div>
            </section>
        </form>
    );
};

export default LoginForm;
