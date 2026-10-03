const form = document.getElementById('registrationForm');
const submitButton = document.getElementById('submitButton');
const message = document.getElementById('message');
const badge = document.getElementById('statusBadge');

function clearErrors() {
  document.querySelectorAll('[data-error-for]').forEach((el) => {
    el.textContent = '';
  });
  message.textContent = '';
  message.className = 'message';
}

function showFieldErrors(errors = {}) {
  Object.entries(errors).forEach(([field, text]) => {
    const target = document.querySelector(`[data-error-for="${field}"]`);
    if (target) target.textContent = text;
  });
}

form.addEventListener('submit', async (event) => {
  event.preventDefault();
  clearErrors();

  const payload = Object.fromEntries(new FormData(form).entries());

  submitButton.disabled = true;
  submitButton.textContent = 'Submitting…';
  badge.textContent = 'Saving';

  try {
    const response = await fetch('/api/registrations', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });

    if (!response.ok) {
      const problem = await response.json();
      showFieldErrors(problem.errors);
      throw new Error(problem.detail || 'Registration could not be completed.');
    }

    const registration = await response.json();
    form.reset();

    message.textContent = `Thanks, ${registration.firstName}. Your registration was saved successfully.`;
    message.className = 'message success';
    badge.textContent = 'Saved';
  } catch (error) {
    message.textContent = error.message;
    message.className = 'message error';
    badge.textContent = 'Needs attention';
  } finally {
    submitButton.disabled = false;
    submitButton.textContent = 'Submit registration';
  }
});
