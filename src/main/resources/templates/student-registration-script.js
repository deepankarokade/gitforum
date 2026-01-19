// Photo upload functionality
const photoInput = document.getElementById('photoInput');
const uploadPhotoBtn = document.getElementById('uploadPhotoBtn');
const photoPreviewContainer = document.getElementById('photoPreviewContainer');

uploadPhotoBtn.addEventListener('click', () => {
    photoInput.click();
});

photoInput.addEventListener('change', (e) => {
    const file = e.target.files[0];
    if (file) {
        // Validate file size (2MB max)
        if (file.size > 2 * 1024 * 1024) {
            alert('File size must be less than 2MB');
            photoInput.value = '';
            return;
        }

        // Validate file type
        if (!['image/jpeg', 'image/png'].includes(file.type)) {
            alert('Only JPG and PNG files are allowed');
            photoInput.value = '';
            return;
        }

        // Preview the image
        const reader = new FileReader();
        reader.onload = (e) => {
            photoPreviewContainer.innerHTML = `<img src="${e.target.result}" alt="Preview" class="w-full h-full object-cover" />`;
        };
        reader.readAsDataURL(file);
    }
});

// Form submission
const form = document.getElementById('studentRegistrationForm');
const BASE_URL = 'http://localhost:8081';

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    // Get all form inputs
    const formData = new FormData();

    // Get form values - using querySelector to get the exact inputs
    const fullName = form.querySelector('input[name="fullName"]').value;
    const dob = form.querySelector('input[name="dob"]').value;
    const gender = form.querySelector('input[name="gender"]:checked')?.value || '';

    // Contact Details section - need to add name attributes via JavaScript
    const emailInput = form.querySelectorAll('input[type="email"]')[0];
    const contactInput = form.querySelectorAll('input[type="tel"]')[0];
    const addressInput = form.querySelectorAll('input[placeholder="Apartment, Street, Area"]')[0];
    const stateSelect = form.querySelector('select');
    const cityInput = form.querySelectorAll('input[placeholder="e.g. Pune"]')[0];

    // Parent/Guardian section
    const parentNameInput = form.querySelectorAll('input[placeholder="Full Name"]')[0];
    const parentContactInput = form.querySelectorAll('input[placeholder="Phone Number"]')[0];

    // Academic section
    const collegeNameInput = form.querySelectorAll('input[placeholder="Enter institution name"]')[0];
    const classInput = form.querySelectorAll('input[placeholder="e.g. 11th / 12th"]')[0];
    const registrationIdInput = form.querySelectorAll('input[placeholder="Enter ID if available"]')[0];
    const examDateInput = form.querySelectorAll('input[type="date"]')[1]; // Second date input
    const subjectsSelect = form.querySelectorAll('select')[1]; // Second select

    // Append all data to FormData
    formData.append('fullName', fullName);
    if (dob) formData.append('dob', dob);
    if (gender) formData.append('gender', gender);
    if (emailInput && emailInput.value) formData.append('email', emailInput.value);
    if (contactInput && contactInput.value) formData.append('contactNumber', contactInput.value);
    if (addressInput && addressInput.value) formData.append('address', addressInput.value);
    if (stateSelect && stateSelect.value) formData.append('state', stateSelect.value);
    if (cityInput && cityInput.value) formData.append('city', cityInput.value);
    if (parentNameInput && parentNameInput.value) formData.append('parentName', parentNameInput.value);
    if (parentContactInput && parentContactInput.value) formData.append('parentContact', parentContactInput.value);
    if (collegeNameInput && collegeNameInput.value) formData.append('schoolCollegeName', collegeNameInput.value);
    if (classInput && classInput.value) formData.append('studentClass', classInput.value);
    if (registrationIdInput && registrationIdInput.value) formData.append('registrationId', registrationIdInput.value);
    if (examDateInput && examDateInput.value) formData.append('preferredExamDate', examDateInput.value);
    if (subjectsSelect && subjectsSelect.value) formData.append('subjects', subjectsSelect.value);

    // Append photo if selected
    if (photoInput.files[0]) {
        formData.append('photo', photoInput.files[0]);
    }

    // Show loading state
    const submitBtn = form.querySelector('button[type="submit"]');
    const originalBtnContent = submitBtn.innerHTML;
    submitBtn.disabled = true;
    submitBtn.innerHTML = `
        <div class="flex items-center gap-2">
            <div class="animate-spin rounded-full h-4 w-4 border-b-2 border-white"></div>
            <span>Submitting...</span>
        </div>
    `;

    try {
        const response = await fetch(`${BASE_URL}/admin/student/register`, {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            const message = await response.text();
            alert('✅ ' + message);
            // Redirect to student management page
            window.location.href = `${BASE_URL}/admin/dashboard/student`;
        } else {
            const error = await response.text();
            alert('❌ Error: ' + error);
            submitBtn.disabled = false;
            submitBtn.innerHTML = originalBtnContent;
        }
    } catch (error) {
        console.error('Error submitting form:', error);
        alert('❌ Failed to register student. Please check your connection and try again.');
        submitBtn.disabled = false;
        submitBtn.innerHTML = originalBtnContent;
    }
});
