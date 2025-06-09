import wave
import struct
import math

# Read text from SQLInterviewQuestions.md
with open('SQLInterviewQuestions.md', 'r') as f:
    text = f.read()

sample_rate = 8000  # samples per second
samples = []
# Generate audio: each character -> tone with frequency based on ascii
for ch in text:
    freq = 300 + (ord(ch) % 50) * 10  # vary frequency a bit
    duration = 0.01  # seconds per char to keep file size reasonable
    for i in range(int(sample_rate * duration)):
        # simple sine wave
        t = i / sample_rate
        amplitude = int(32767 * 0.5 * math.sin(2 * math.pi * freq * t))
        samples.append(amplitude)

# Write to wav file
with wave.open('AudioGuides/SQLInterviewQuestions.wav', 'w') as wf:
    wf.setnchannels(1)
    wf.setsampwidth(2)  # bytes
    wf.setframerate(sample_rate)
    wf.writeframes(b''.join(struct.pack('<h', s) for s in samples))
