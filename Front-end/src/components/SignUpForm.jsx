import React, { useState } from "react";
import { IoIosDocument, IoIosHome, IoIosMail, IoIosMedical, IoIosPhonePortrait, IoIosSubway } from "react-icons/io";
import { IoEye } from "react-icons/io5";
import { IoIosClose } from "react-icons/io";
import { CgProfile } from "react-icons/cg";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const SignUpForm = () => {
    const [pass, setPass] = useState(false);
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        phone: "",
        password: "",
        department: "Computer Applications",
        role: "PATIENT",
        age: "",
        gender: "",
        medicalLicense: "",
        specialization: "",
        clinicName: "",
        address: "",
        city: "",
    });
   // const navigate = useNavigate();
    const [message, setMessage] = useState("");

    const togglePass = () => {
        setPass((prev) => !prev);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };
    

    const handleSubmit = async (e) => {
        e.preventDefault();
        const endpoint = formData.role === "DOCTOR" ? "addDoctor" : "addPatient";
        
        try {
            const response = await fetch(`http://localhost:8080/api/auth/${endpoint}`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData),

            });

            if (response.ok) {
                setMessage("Registration successful! Please login to continue.");
                alert("Registration successful! Please login to continue")
               // navigate('/loginform');
            } else {
                const error = await response.text();
                setMessage(`Error: ${error}`);
            }
        } catch (error) {
            setMessage(`Error: ${error.message}`);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="flex flex-col items-center justify-center 2xl:gap-8 ssm:gap-5 sm:gap-6">
            <h1 className="lg:text-4xl ssm:text-[2.6rem] font-medium">Register Now</h1>
            <section className="flex flex-row items-center gap-8 sm:py-0 ssm:py-[1rem]">
                <div className="flex items-center gap-2 text-xl font-medium">
                    <input type="radio" id="patient" name="role" value="PATIENT" checked={formData.role === "PATIENT"} onChange={handleChange} />
                    <label htmlFor="patient">Patient</label>
                </div>
                <div className="flex items-center gap-2 text-xl font-medium">
                    <input type="radio" id="doctor" name="role" value="DOCTOR" checked={formData.role === "DOCTOR"} onChange={handleChange} />
                    <label htmlFor="doctor">Doctor</label>
                </div>
            </section>
            <section className="flex flex-col items-center gap-4 sm:mt-0 ssm:mt-[2rem]">
                <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                    <input
                        type="text"
                        name="name"
                        placeholder="Name"
                        className="text-dark bg-inherit outline-none border-none text-lg"
                        value={formData.name}
                        onChange={handleChange}
                    />
                    <CgProfile className="text-xl" />
                </div>                
                <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                    <input
                        type="email"
                        name="email"
                        placeholder="Email"
                        className="text-dark bg-inherit outline-none border-none text-lg"
                        value={formData.email}
                        onChange={handleChange}
                    />
                    <IoIosMail className="text-xl" />
                </div>
                <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                    <input
                        type="phone"
                        name="phone"
                        placeholder="Phone"
                        className="text-dark bg-inherit outline-none border-none text-lg"
                        value={formData.phone}
                        onChange={handleChange}
                    />
                    <IoIosPhonePortrait className="text-xl" />
                </div>
                <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                    <input
                        type={pass ? "text" : "password"}
                        name="password"
                        placeholder="Password"
                        className="text-dark bg-inherit outline-none border-none text-lg"
                        value={formData.password}
                        onChange={handleChange}
                    />
                    {pass ? (
                        <IoIosClose className="text-xl cursor-pointer" onClick={togglePass} />
                    ) : (
                        <IoEye className="text-xl cursor-pointer" onClick={togglePass} />
                    )}
                </div>
                {formData.role==="PATIENT" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <input
                            type="number"
                            name="age"
                            placeholder="Age"
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.age}
                            onChange={handleChange}
                        />
                        <IoIosMail className="text-xl" />
                    </div>
                )}
                {formData.role==="PATIENT" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <select
                            name="gender"
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.gender}
                            onChange={handleChange}
                        >
                            <option value="" disabled>Gender</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                        <IoIosMail className="text-xl" />
                    </div>
                )}

                {formData.role==="DOCTOR" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <input
                            type="text"
                            name="medicalLicense"
                            placeholder="Medical License No."
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.medicalLicense}
                            onChange={handleChange}
                        />
                        <IoIosDocument className="text-xl" />
                    </div>
                )}
                {formData.role==="DOCTOR" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <input
                            type="text"
                            name="specialization"
                            placeholder="Specialization"
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.specialization}
                            onChange={handleChange}
                        />
                        <IoIosDocument className="text-xl" />
                    </div>
                )} 
                {formData.role==="DOCTOR" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <input
                            type="text"
                            name="clinicName"
                            placeholder="Clinic Name"
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.clinicName}
                            onChange={handleChange}
                        />
                        <IoIosMedical className="text-xl" />
                    </div>
                )} 
                {/* {formData.role==="DOCTOR" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <input
                            type="text"
                            name="doorNo"
                            placeholder="Door No."
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.doorNo}
                            onChange={handleChange}
                        />
                        <IoIosHome className="text-xl" />
                    </div>
                )}  */}
                {formData.role==="DOCTOR" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <input
                            type="text"
                            name="address"
                            placeholder="Address"
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.address}
                            onChange={handleChange}
                        />
                        <IoIosSubway className="text-xl" />
                    </div>
                )}            
                {formData.role==="DOCTOR" && (
                    <div className="bg-lightgrey px-8 py-4 rounded-full flex items-center sm:gap-12 ssm:gap-2">
                        <select
                            name="city"
                            className="text-dark bg-inherit outline-none border-none text-lg"
                            value={formData.city}
                            onChange={handleChange}
                        >
                            <option value="" disabled>City</option>
                            <option value="Chennai">Chennai</option>
                            <option value="Bangalore">Bangalore</option>
                            <option value="Mumbai">Mumbai</option>
                        </select>
                        <IoIosMail className="text-xl" />
                    </div> 
                )}                      
                                                                                               
            </section>            
            <section className="flex flex-col items-center gap-4">
                <button
                    type="submit"
                    aria-label="SignUp"
                    className="bg-dark text-lg font-medium sm:px-12 sm:py-2 ssm:px-24 ssm:py-[0.6rem] text-light rounded-full"
                    onClick={handleSubmit} 
                >
                    Sign Up
                </button>
                <div className="flex gap-4">
                    <h2 className="text-lightblack">Have an account?</h2>
                    <Link to={"/"} className="hover:underline cursor-pointer underline-offset-[3px] font-semibold">
                        Sign In
                    </Link>
                </div>
                {message && <p className="text-red-500 mt-4">{message}</p>}
            </section>
        </form>
    );
};

export default SignUpForm;
